package Chess.Model.Moves;
/*
Class that hold information about move (change of state of game) that player
can make.
 */

import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.ChessPieces.Knight;
import Chess.Model.ChessUtil;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

public class Move {
    public Position from, to;

    // true if pawn should be promoted to queen, knight, rook or bishop.
    public boolean isPromotion;

    // should be one of {queen, knight, rook, bishop}
    public ChessPiece promoteTo;

    Move(){}

    public Move (Position from, Position to){
        this.from = from;
        this.to = to;
    }

    public Move(Position from, Position to,
         ChessPiece chosenChessPiece){
        this.from = from;
        this.to = to;
        this.promoteTo = chosenChessPiece;
        isPromotion = true;
    }

    @Override
    public String toString() {
        if(isPromotion){
            return "from " + from.toString() + " to " + to.toString() +
                    " promoted to " + promoteTo.toString();
        }
        return "from " + from.toString() + " to " + to.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return  (obj instanceof Move)&&
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

    public boolean isBreakingRules(){
        if(isOutsideBoard()){
            return true;
        }

        ChessPiece movedChessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(from);

        if(movedChessPiece.getChessColour() ==
                StateOfGame.chessboard.
                        getChessPieceOnPosition(to).getChessColour()){
            return true;
        }

        // checks if there is figure moved.
        if(movedChessPiece instanceof EmptySquare){
            return true;
        }


        if(differenceOnXCoordinate() + differenceOnYCoordinate() == 0){
            return true;
        }
        if(movedChessPiece.isBreakingRules(this)){
            return true;
        }

        // checks if move is not blocked by some chess piece
        return !(StateOfGame.chessboard.getChessPieceOnPosition(from)
                instanceof Knight) && ChessUtil.isMovePassingThroughFigure(this);
    }
}
