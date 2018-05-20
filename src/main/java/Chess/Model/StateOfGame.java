package Chess.Model;

import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Moves.HistoryOfMoves;
import Chess.Model.Moves.Move;

import java.io.Serializable;
import java.util.List;

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

    // function initialize state of game and run moves saved in history of
    // moves - can be used to load state of game from saved game and
    // is used to undo move
    private static void reload(){
        StateOfGame.variant.initializeStateOfGame();
        List <Move> moves = historyOfMoves.listOfPreviousMoves();
        for(Move v : moves){
            StateOfGame.variant.changeState(v);
        }
    }

    //undo last move - make all previous moves, but last.
    public static void undoMove(){
        historyOfMoves.undoMove();
        reload();
    }
}
