import java.util.ArrayList;

public class King extends ChessPiece{

    {label = 'W';}

    King(ChessColour a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
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
