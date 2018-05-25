package Chess.Model.Moves;

import Chess.Model.StateOfGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Class holds information about moves made in game.
 */
public class HistoryOfMoves implements Serializable {
    private final ArrayList<Move> lastMoves = new ArrayList<>();
    private final ArrayList<String> chessboardsStates =
            new ArrayList<>();

    public List<Move> listOfPreviousMoves() {
        return new ArrayList<>(lastMoves);
    }

    public List<String> listOfChessboardStates() {
        return Collections.unmodifiableList(chessboardsStates);
    }

    public void addMove(Move v) {
        if(v instanceof SpecialMove){
            lastMoves.add(v);
            chessboardsStates.add(StateOfGame.chessboard.toString());
            return;
        }
        Move t;
        if (v.isPromotion) {
            t = new Move(v.from, v.to, v.promoteTo);
        } else {
            t = new Move(v.from, v.to);
        }
        lastMoves.add(t);
        chessboardsStates.add(StateOfGame.chessboard.toString());
    }

    public Move lastMove() {
        if (lastMoves.isEmpty()) {
            return null;
        }
        return lastMoves.get(lastMoves.size() - 1);
    }

    public void clear() {
        lastMoves.clear();
        chessboardsStates.clear();
    }

    // delete last saved move in instance of class
    public void undoMove() {
        int n = lastMoves.size();
        if (n == 0) {
            return;
        }
        lastMoves.remove(n - 1);
        chessboardsStates.remove(n - 1);
    }
}
