import java.util.ArrayList;
/*
Class uses null object pattern. Class represent that there is no figure
on some position. Class is also used to print empty square.
 */
public class EmptySquare extends ChessPiece{

    {label = '.';}

    EmptySquare(Position b){
        super(null, b);
    }

    EmptySquare(Color a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        return null;
    }

}
