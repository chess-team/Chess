package Chess.Model;

import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Moves.HistoryOfMoves;

import java.io.Serializable;

/*
Singleton class that have information about state of game, variant of game and
history of moves.
*/

@SuppressWarnings("EmptyMethod")
public class StateOfGame implements Serializable{

    private static final StateOfGame realStateOfGame = new StateOfGame();

    private StateOfGame(){}

    // return only instance of singleton class
    @SuppressWarnings("unused")
    public static StateOfGame getInstance(){
        return realStateOfGame;
    }

    public static final HistoryOfMoves historyOfMoves = new HistoryOfMoves();

    public static Chessboard chessboard = new ClassicChessboard();

    public static VariantOfGame variant = new ClassicChess();

    static {
        variant.initializeStateOfGame();
    }
    @SuppressWarnings("unused")
    public static StateOfGameplay stateOfGameplay;

    // Method that parse string in format of toString() method and initialize
    // fields of class. This method is used to load saved game from hard drive.
    @SuppressWarnings("unused")
    void loadStateOfGame(String savedState){
    }

    // Method that save all information about state of game to string.
    // This method is used to save state of game to hard drive.
    @Override
    public String toString() {
        return super.toString();
    }
}
