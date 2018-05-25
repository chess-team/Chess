package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.*;
import Chess.Model.Moves.*;

import java.util.ArrayList;
import java.util.List;

/*
Class that implements rules of Classic chess.
 */
public class ClassicChess extends VariantSimilarToClassicChess {

    public static boolean isCheck = true;

    private ChessColour colorOfLastMovedPiece = ChessColour.BLACK;


    // return true if move is correct.
    public boolean validateMove(Move move) {

        if (move instanceof Castling) {
            return validateMove((Castling) move);
        }

        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(move.from);

        if (figure instanceof King &&
                move.differenceOnXCoordinate() > 1) {
            int sign = ChessUtil.signum(move.to.x - move.from.x);
            return validateMove(new Castling(move.to.translateByVector(sign, 0)));
        }

        if (!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }
        if (move.promoteTo != null && !(figure instanceof Pawn)) {
            return false;
        }
        ChessColour colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getChessColour();

        return move.getMoveColor() == StateOfGame.stateOfGameplay
                && !isKingUnderAttackAfterMove(colorOfPlayer, move);

    }

    // return true if castling move is correct.
    private boolean validateMove(Castling move) {
        if (move.isBreakingRules()) {
            return false;
        }

        Position towerPosition = move.getTowerPosition();
        if (towerPosition == null) {
            return false;
        }
        if (towerPosition.x < 0 || towerPosition.x >= 8) {
            return false;
        }

        ChessPiece tower = StateOfGame.chessboard.
                getChessPieceOnPosition(towerPosition);
        if (!(tower instanceof Rook)) {
            return false;
        }
        ChessColour colorOfPlayer = tower.getChessColour();

        Position kingPosition = ChessUtil.getKingPosition(colorOfPlayer);
        if (kingPosition == null) {
            return false;
        }
        for (Move v : StateOfGame.historyOfMoves.listOfPreviousMoves()) {
            if (v instanceof SpecialMove) {
                if (v instanceof Castling) {
                    if (((Castling) v).getTowerPosition().y ==
                            towerPosition.y)
                        return false;
                }
                continue;
            }
            if (v.from.equals(kingPosition)) {
                return false;
            }
            if (v.from.equals(towerPosition)) {
                return false;
            }
        }
        int sign = ChessUtil.signum(towerPosition.x - kingPosition.x);
        int dist = Math.abs(towerPosition.x - kingPosition.x);
        if (dist < 3) {
            return false;
        }
        for (int i = 1; i < dist; ++i) {
            Position temp = kingPosition.
                    translateByVector(i * sign, 0);
            if (isPlaceUnderAttack(temp, colorOfLastMovedPiece) ||
                    !(StateOfGame.chessboard.getChessPieceOnPosition(temp)
                            instanceof EmptySquare)) {
                return false;
            }
        }

        return move.getMoveColor() == StateOfGame.stateOfGameplay;
    }

    public void initializeStateOfGame() {
        isCheck = true;
        setClassicState();
        Castling.castlingDisabled = false;
        colorOfLastMovedPiece = ChessColour.BLACK;
        setLineOfPawns(1, ChessColour.WHITE);
        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(0, ChessColour.WHITE);
        setLineOfFigures(7, ChessColour.BLACK);
    }


    void swapColor() {
        switch (colorOfLastMovedPiece) {
            case BLACK:
                colorOfLastMovedPiece = ChessColour.WHITE;
                break;
            case WHITE:
                colorOfLastMovedPiece = ChessColour.BLACK;
                break;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private boolean validateEnPassantMove(Move move) {
        int differenceOnXCoordinate = move.to.x - move.from.x;
        int differenceOnYCoordinate = move.to.y - move.from.y;
        Position wantedPreviousMoveTo = move.from.translateByVector(
                differenceOnXCoordinate, 0);
        if (!(StateOfGame.chessboard.
                getChessPieceOnPosition(wantedPreviousMoveTo)
                instanceof Pawn)) {
            return false;
        }
        Position wantedPreviousMoveFrom = move.from.translateByVector(
                differenceOnXCoordinate, 2 * differenceOnYCoordinate);

        Move wantedPreviousMove =
                new Move(wantedPreviousMoveFrom, wantedPreviousMoveTo);
        Move previousMove = StateOfGame.historyOfMoves.lastMove();
        return wantedPreviousMove.equals(previousMove);
    }

    public void changeState(Move change) {
        if (change instanceof Castling) {
            changeState((Castling) change);
            return;
        }

        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(change.from);

        if (figure instanceof King &&
                change.differenceOnXCoordinate() > 1) {
            int sign = ChessUtil.signum(change.to.x - change.from.x);
            changeState(new Castling(change.to.translateByVector(sign, 0)));
            return;
        }

        ChessPiece targetPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(change.to);

        swapColor();
        if (validateEnPassantMove(change)) {
            Position previousMoveTo = StateOfGame.historyOfMoves.lastMove().to;
            targetPiece = StateOfGame.chessboard.
                    getChessPieceOnPosition(previousMoveTo);

            StateOfGame.chessboard.setFigure(new EmptySquare(previousMoveTo));
        }
        Character c = targetPiece.toString().charAt(0);
        if (!c.equals('.')) {
            StateOfGame.capturedPieces.add(c);
        }
        StateOfGame.chessboard.moveFigure(change);
        if (change.promoteTo != null) {
            StateOfGame.chessboard.setFigure(change.promoteTo);
        }
        StateOfGame.historyOfMoves.addMove(change);
        changeStateOfGameplay();
    }

    protected void changeStateOfGameplay() {
        swapPlayerColor();
        inCaseOfEndOfGame();
    }


    private void changeState(Castling change) {
        swapColor();
        Position towerPosition = change.getTowerPosition();
        ChessColour colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(towerPosition).getChessColour();

        Position kingPosition = ChessUtil.getKingPosition(colorOfPlayer);
        if (kingPosition == null) {
            throw new NullPointerException();
        }
        int sign = ChessUtil.signum(towerPosition.x - kingPosition.x);
        Position newKingPosition = towerPosition.translateByVector(-sign, 0);
        Position newTowerPosition = newKingPosition.translateByVector(-sign, 0);
        StateOfGame.chessboard.moveFigure(
                new Move(kingPosition, newKingPosition));

        StateOfGame.chessboard.moveFigure(
                new Move(towerPosition, newTowerPosition));

        StateOfGame.historyOfMoves.addMove(change);
        changeStateOfGameplay();
    }

    void inCaseOfEndOfGame() {
        ChessColour colorOfPossibleLoser = ChessColour.WHITE;
        if (StateOfGame.stateOfGameplay == StateOfGameplay.BLACK_MOVE) {
            colorOfPossibleLoser = ChessColour.BLACK;
        }
        if (ChessUtil.listOfAllMoves(colorOfPossibleLoser).isEmpty()) {
            if (isKingUnderAttack(colorOfPossibleLoser)) {
                switch (colorOfPossibleLoser) {
                    case WHITE:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.BLACK_WON;
                        return;
                    case BLACK:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.WHITE_WON;
                        return;
                }
            }
        }
        drawRuleNoPossibleMove(colorOfPossibleLoser);
        drawRule50MovesWithoutCapture();
        drawRule3TimesSamePosition();
    }


    private void drawRule50MovesWithoutCapture() {
        List<String> states = StateOfGame.historyOfMoves
                .listOfChessboardStates();
        int n = states.size();
        if (n > 50) {
            boolean isDraw;
            String state = states.get(n - 50);
            int m = state.length();
            int emptySquareCountBefore = 0;
            for (int i = 0; i < m; ++i) {
                if (state.charAt(i) == '.') {
                    emptySquareCountBefore++;
                }
            }
            state = StateOfGame.chessboard.toString();
            m = state.length();
            int emptySquareCountNow = 0;
            for (int i = 0; i < m; ++i) {
                if (state.charAt(i) == '.') {
                    emptySquareCountNow++;
                }
            }
            isDraw = (emptySquareCountBefore == emptySquareCountNow);
            if (isDraw) {
                StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
            }
        }
    }

    void drawRuleNoPossibleMove(ChessColour colorOfPossibleLoser) {
        if (ChessUtil.listOfAllMoves(colorOfPossibleLoser).isEmpty()) {
            StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
        }
    }

    void drawRule3TimesSamePosition() {
        String stateOfChessboard = StateOfGame.chessboard.toString();
        List<String> previousStates = StateOfGame.historyOfMoves.
                listOfChessboardStates();
        int count = 0;
        for (String temp : previousStates) {
            if (temp.equals(stateOfChessboard)) ++count;
        }
        if (count >= 3) {
            StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
        }
    }

    @Override
    public SpecialMoves getSpecialMoves() {
        return playerColor -> {
            ArrayList<Move> resultList = new ArrayList<>();
            for (int i = 0; i <= 7; i += 7) {
                for (int j = 0; j <= 7; j += 7) {
                    Castling move = new Castling(new Position(i, j));
                    if (StateOfGame.variant.validateMove(move)) {
                        resultList.add(move);
                    }
                }
            }
            return resultList;
        };
    }
}
