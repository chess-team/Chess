import java.util.ArrayList;

public class Queen extends ChessPiece {

    {label = 'Q';}

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = -8; i <= 8; ++i) {
            for(int j = -8; j <= 8; ++j) {
                if(i == 0 && j == 0) continue;
                Move v = new Move(getPosition(),
                        getPosition().translateByVector(i,j));
                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }
            }
        }

        return resultList;
    }
}
