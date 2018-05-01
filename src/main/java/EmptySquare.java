import java.util.ArrayList;
/*
Class uses null object pattern. Class represent that there is no figure
on some position. Class is also used to print empty square.
 */
@SuppressWarnings("unused")
public class EmptySquare extends ChessPiece{

    {label = '.';}

    EmptySquare(Position b){

        super(null, b);
    }

    EmptySquare(ChessColour a, Position b){

        super(null,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {

        return new ArrayList<Move>();
    }

}
