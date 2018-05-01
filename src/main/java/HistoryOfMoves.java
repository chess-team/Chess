import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Class holds information about moves made in game.
 */
public class HistoryOfMoves {
    private final ArrayList <Move> lastMoves = new ArrayList<Move>();
    private final ArrayList <String> chessboardsStates =
            new ArrayList<String>();

    public List <Move> listOfPreviousMoves(){
        return Collections.unmodifiableList(lastMoves);
    }

    public void addMove(Move v){
        lastMoves.add(v);
        chessboardsStates.add(StateOfGame.chessboard.toString());
    }

    public Move lastMove(){
        if(lastMoves.isEmpty()){
            return null;
        }
        return lastMoves.get(lastMoves.size() - 1);
    }

    //TODO
    public void undoMove(){
        chessboardsStates.remove(chessboardsStates.size() - 1);
        //StateOfGame.chessboard =
        //        chessboardsStates.get(chessboardsStates.size() - 1);
    }
}
