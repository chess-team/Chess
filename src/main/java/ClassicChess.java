import java.util.ArrayList;
import java.util.List;

/*
Class that implements rules of Classic chess.
 */
public class ClassicChess extends VariantSimilarToClassicChess {

    Color colorOfLastMovedPiece = Color.BLACK;

    private final ArrayList <String> listOfCapturedPieces =
            new ArrayList<String>();

    // return true if move is correct.
    public boolean validateMove(Move move) {

        if(move instanceof Castling){
            return validateMove((Castling) move);
        }

        if(!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }

        Color colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getColor();

        if(colorOfPlayer == colorOfLastMovedPiece){
            return false;
        }

        //noinspection RedundantIfStatement
        if(isKingUnderAttackAfterMove(colorOfPlayer, move)){
            return false;
        }

        return true;
    }

    // return true if castling move is correct.
    private boolean validateMove(Castling move) {

        Position towerPosition = move.getTowerPosition();
        if(towerPosition == null) {
            return false;
        }
        if(towerPosition.x < 0 || towerPosition.x >= 8){
            return false;
        }

        ChessPiece tower = StateOfGame.chessboard.
                getChessPieceOnPosition(towerPosition);
        if(!(tower instanceof Rook)){
            return false;
        }
        Color colorOfPlayer = tower.getColor();

        Position kingPosition = ChessUtil.getKingPosition(colorOfPlayer);
        if(kingPosition == null) {
            return false;
        }
        for(Move v : StateOfGame.historyOfMoves.listOfPreviousMoves()){
            if(v instanceof SpecialMove){
                if(v instanceof Castling){
                    if(((Castling) v).getTowerPosition().y ==
                            towerPosition.y)
                        return false;
                }
                continue;
            }
            if(v.from.equals(kingPosition)){
                return false;
            }
            if(v.from.equals(towerPosition)){
                return false;
            }
        }
        int sign = ChessUtil.signum(towerPosition.x - kingPosition.x);
        int dist = Math.abs(towerPosition.x - kingPosition.x);
        for (int i = 1; i < dist; ++i){
            if(isPlaceUnderAttack(kingPosition.
                    translateByVector(i*sign, 0), colorOfLastMovedPiece)){
                return false;
            }
        }

        return colorOfPlayer != colorOfLastMovedPiece;
    }

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(1,Color.WHITE);
        setLineOfPawns(6,Color.BLACK);
        setLineOfFigures(0, Color.WHITE);
        setLineOfFigures(7, Color.BLACK);
    }


    void swapColor(){
        switch (colorOfLastMovedPiece){
            case BLACK:
                colorOfLastMovedPiece = Color.WHITE;
                break;
            case WHITE:
                colorOfLastMovedPiece = Color.BLACK;
                break;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    boolean validateEnPassantMove(Move move){
        int differenceOnXCoordinate = move.to.x - move.from.x;
        int differenceOnYCoordinate = move.to.y - move.from.y;
        Position wantedPreviousMoveTo = move.from.translateByVector(
                differenceOnXCoordinate,0);
        if(!(StateOfGame.chessboard.
                getChessPieceOnPosition(wantedPreviousMoveTo)
                instanceof Pawn)){
            return false;
        }
        Position wantedPreviousMoveFrom = move.from.translateByVector(
                differenceOnXCoordinate,2*differenceOnYCoordinate);

        Move wantedPreviousMove =
                new Move(wantedPreviousMoveFrom,wantedPreviousMoveTo);
        Move previousMove = StateOfGame.historyOfMoves.lastMove();
        return wantedPreviousMove.equals(previousMove);
    }

    public void changeState(Move change) {
        if(change instanceof Castling){
            changeState((Castling) change);
            return;
        }
        ChessPiece targetPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(change.to);

        swapColor();
        if(validateEnPassantMove(change)){
            Position previousMoveTo = StateOfGame.historyOfMoves.lastMove().to;
            targetPiece = StateOfGame.chessboard.
                    getChessPieceOnPosition(previousMoveTo);

            StateOfGame.chessboard.setFigure(new EmptySquare(previousMoveTo));
        }
        listOfCapturedPieces.add(targetPiece.toString());
        StateOfGame.chessboard.moveFigure(change);
        if(change.promoteTo != null){
            StateOfGame.chessboard.setFigure(change.promoteTo);
        }
        StateOfGame.historyOfMoves.addMove(change);
        inCaseOfEndOfGame();
    }

    private void changeState(Castling change) {
        swapColor();
        Position towerPosition = change.getTowerPosition();
        Color colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(towerPosition).getColor();

        Position kingPosition = ChessUtil.getKingPosition(colorOfPlayer);
        if(kingPosition == null) {
            throw new NullPointerException();
        }
        int sign = ChessUtil.signum(towerPosition.x - kingPosition.x);
        int dist = Math.abs(towerPosition.x - kingPosition.x);
        Position newKingPosition = towerPosition.translateByVector(-sign,0);
        Position newTowerPosition = newKingPosition.translateByVector(-sign,0);
        StateOfGame.chessboard.moveFigure(
                new Move(kingPosition, newKingPosition));

        StateOfGame.chessboard.moveFigure(
                new Move(towerPosition, newTowerPosition));

        StateOfGame.historyOfMoves.addMove(change);
        inCaseOfEndOfGame();
    }

    private void inCaseOfEndOfGame(){
        Color colorOfPossibleLoser = ChessUtil.
                getOtherColor(colorOfLastMovedPiece);
        if(ChessUtil.listOfAllMoves(colorOfPossibleLoser).isEmpty()) {
            if (isKingUnderAttack(colorOfPossibleLoser)) {
                switch (colorOfPossibleLoser){
                    case WHITE:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.BLACK_WON;
                        break;
                    case BLACK:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.WHITE_WON;
                        break;
                }
            }
            else {
                StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
            }
        }
        int n = listOfCapturedPieces.size();
        if(n > 50){
            boolean isDraw = true;
            for(int i = n - 50; i < n; ++i){
                if(!listOfCapturedPieces.get(i).equals(".")){
                    isDraw = false;
                }
            }
            if(isDraw){
                StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
            }
        }

        String stateOfChessboard = StateOfGame.chessboard.toString();
        List<String> previousStates = StateOfGame.historyOfMoves.
                listOfChessboardStates();

        int count = 0;
        for(String temp : previousStates){
            if(temp.equals(stateOfChessboard))++count;
        }
        if(count >= 3){
            StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
        }
    }

    @Override
    public SpecialMoves getSpecialMoves() {
        return new SpecialMoves() {
            public ArrayList<Move> listOfPossibleMoves(Color playerColor) {
                ArrayList <Move> resultList = new ArrayList<Move>();
                for(int i = 0; i <= 7; i += 7){
                    for(int j = 0; j <= 7; j += 7){
                        Castling move = new Castling(new Position(i, j));
                        if(StateOfGame.variant.validateMove(move)){
                            resultList.add(move);
                        }
                    }
                }
                return resultList;
            }
        };
    }
}
