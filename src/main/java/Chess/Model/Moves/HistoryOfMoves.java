package Chess.Model.Moves;

import Chess.Model.StateOfGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Class holds information about moves made in game.
 */
public class HistoryOfMoves {
    private final ArrayList <Move> lastMoves = new ArrayList<>();
    private final ArrayList <String> chessboardsStates =
            new ArrayList<>();

    public List <Move> listOfPreviousMoves(){
        return Collections.unmodifiableList(lastMoves);
    }

    public List <String> listOfChessboardStates(){
        return Collections.unmodifiableList(chessboardsStates);
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

    public void clear(){
        lastMoves.clear();
        chessboardsStates.clear();
    }

}
