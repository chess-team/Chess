
/*
Class that implements rules of Classic chess.
 */
public class ClassicChess extends VariantSimilarToClassicChess {

    Color colorOfLastMovedPiece = Color.BLACK;

    // return true if move is correct.
    public boolean validateMove(Move move) {

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
    public boolean validateEnPassantMove(Move move){
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
        swapColor();
        if(validateEnPassantMove(change)){
            Position previousMoveTo = StateOfGame.historyOfMoves.lastMove().to;
            StateOfGame.chessboard.setFigure(new EmptySquare(previousMoveTo));
        }
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);

    }
}
