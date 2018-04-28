import java.util.ArrayList;

public class Rook extends ChessPiece {

    {label = 'R';}

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = -8; i <= 8; ++i) {
            if(i == 0) continue;
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i,0));
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }

            v = new Move(getPosition(),
                    getPosition().translateByVector(0,i));
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }

        return resultList;
    }
}
