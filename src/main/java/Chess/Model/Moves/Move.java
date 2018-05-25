package Chess.Model.Moves;
/*
Class that hold information about move (change of state of game) that player
can make.
 */

import Chess.Model.*;
import Chess.Model.ChessPieces.*;

public class Move {
    public Position from, to;

    // true if pawn should be promoted to queen, knight, rook or bishop.
    public boolean isPromotion;

    // should be one of {queen, knight, rook, bishop}
    public ChessPiece promoteTo;

    Move() {
    }

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Move(Position from, Position to, ChessPiece chosenChessPiece) {
        this.from = from;
        this.to = to;
        ChessPiece t = chosenChessPiece;
        try {
            t = chosenChessPiece.getClass().getConstructor(ChessColour.class, Position.class).
                    newInstance(chosenChessPiece.getChessColour(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.setPosition(to);
        this.promoteTo = t;
        isPromotion = true;
    }

    @Override
    public String toString() {
        if (isPromotion) {
            return "from " + from.toString() + " to " + to.toString() +
                    " promoted to " + promoteTo.toString();
        }
        return "from " + from.toString() + " to " + to.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Move) &&
                obj.toString().equals(toString());
    }

    public int differenceOnXCoordinate() {
        return Math.abs(from.x - to.x);
    }

    public int differenceOnYCoordinate() {
        return Math.abs(from.y - to.y);
    }

    // checks if move is outside board
    private boolean isOutsideBoard() {
        return to == null || from == null || to.x < 0 ||
                to.x >= StateOfGame.chessboard.getXWidth() || to.y < 0
                || to.y >= StateOfGame.chessboard.getYWidth();

    }

    public boolean isBreakingMoveRules() {
        if (isOutsideBoard()) {
            return true;
        }

        ChessPiece movedChessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(from);

        if (movedChessPiece.getChessColour() ==
                StateOfGame.chessboard.
                        getChessPieceOnPosition(to).getChessColour()) {
            return true;
        }

        // checks if there is figure moved.
        if (movedChessPiece instanceof EmptySquare) {
            return true;
        }


        if (differenceOnXCoordinate() + differenceOnYCoordinate() == 0) {
            return true;
        }
        if (movedChessPiece.isBreakingRules(this)) {
            return true;
        }
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(from);
        if (promoteTo != null && !(figure instanceof Pawn)) {
            return true;
        }
        // checks if move is not blocked by some chess piece
        return !(figure instanceof Knight)
                && ChessUtil.isMovePassingThroughFigure(this);
    }

    public boolean isNotBreakingRules() {
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(from);

        if (figure instanceof King &&
                differenceOnXCoordinate() > 1) {
            int sign = ChessUtil.signum(to.x - from.x);
            return StateOfGame.variant.validateMove(new Castling(to.translateByVector(sign, 0)));
        }

        return !isBreakingMoveRules() && getMoveColor() == StateOfGame.stateOfGameplay;

    }

    StateOfGameplay getMoveColor() {
        ChessPiece movedChessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(from);
        switch (movedChessPiece.getChessColour()) {
            case WHITE:
                return StateOfGameplay.WHITE_MOVE;
            case BLACK:
                return StateOfGameplay.BLACK_MOVE;
        }
        return null;
    }
}
