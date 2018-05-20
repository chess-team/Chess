package Chess.Model;

import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Moves.HistoryOfMoves;

import java.io.Serializable;

/*
Singleton class that have information about state of game, variant of game and
history of moves.
*/

public class StateOfGame implements Serializable{

    private StateOfGame(){}

    public static final HistoryOfMoves historyOfMoves = new HistoryOfMoves();

    public static Chessboard chessboard = new ClassicChessboard();

    public static VariantOfGame variant = new ClassicChess();

    static {
        variant.initializeStateOfGame();
    }

    public static StateOfGameplay stateOfGameplay;

    public static StateOfGameplay getStateOfGameplay() {
        return stateOfGameplay;
    }
}
