import java.util.ArrayList;

public class Pawn extends ChessPiece {

    {label = 'P';}

    Pawn(Color a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        int i = 1;

        for(int j = -1; j <= 1; ++j) {
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i,j));

            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }

        return resultList;
    }
}
