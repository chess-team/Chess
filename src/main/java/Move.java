
/*
Class that hold information about move (change of state of game) that player
can make.
 */

public class Move {
    public Position from, to;

    // true if pawn should be promoted to queen, knight, rook or bishop.
    boolean isPromotion;

    // should be one of {queen, knight, rook, bishop}
    ChessPiece promoteTo;

    Move(){}

    Move (Position from, Position to){
        this.from = from;
        this.to = to;
    }

    Move(Position from, Position to,
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
}
