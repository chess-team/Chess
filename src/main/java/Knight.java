import java.util.ArrayList;

public class Knight extends ChessPiece {

    {label = 'K';}

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = -2; i <= 2; i += 4) {
            for(int j = -1; j <= 1; j += 2) {
                Move v = new Move(getPosition(),
                        getPosition().translateByVector(i,j));

                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }

                v = new Move(getPosition(),
                        getPosition().translateByVector(j,i));

                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }
            }
        }

        return resultList;
    }
}
