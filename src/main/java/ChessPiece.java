import java.util.ArrayList;

/*
Class holds information about chess pieces.
 */

public abstract class ChessPiece {

    private final Color color;

    private Position positionOnChessboard;

    char label;

    abstract public ArrayList<Move> listOfPossibleMoves();

    public Position getPosition(){

        return positionOnChessboard;
    }

    public Color getColor(){

        return this.color;
    }

    public void setPosition(Position a){

        this.positionOnChessboard = a;
    }

    // method used in printing chessBoard in text UI.
    @Override
    public String toString() {
        String ret = Character.toString(label);
        if(this.color == Color.BLACK)
            ret = Character.toString(Character.toLowerCase(label));
        return ret;
    }

    ChessPiece(Color a, Position b){
        this.color = a;
        this.positionOnChessboard = b;
    }
}
