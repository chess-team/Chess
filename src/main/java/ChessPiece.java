import java.util.ArrayList;

/*
Class that holds information about chess piece. Chessboard have 64 squares.
 */
public abstract class ChessPiece {

    protected Color color;

    protected Position positionOnChessboard;

    protected char label;

    abstract public ArrayList<Move> listOfPossibleMoves();

    public Position getPosition(){
        return positionOnChessboard;
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
