package Chess.Model;

import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Moves.HistoryOfMoves;
import Chess.Model.Moves.Move;

import java.io.Serializable;
import java.util.ArrayList;
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

    public static ArrayList<Character> capturedPieces;

    //return list of all pieces also captured
    public static List <Character> getListOfLabelsOfAllPieces(){
        List <Character> resultList = new ArrayList<>(capturedPieces);
        String stringChessboard = chessboard.toString();
        for(int i = 0; i < stringChessboard.length(); ++i){
            Character c = stringChessboard.charAt(i);
            if(c != '.' && c != '\n')resultList.add(c);
        }
        return resultList;
    }

    public static List <Character> getListOfLabelsOfCapturedPieces(){
        return capturedPieces;
    }

    // function initialize state of game and run moves saved in history of
    // moves - can be used to load state of game from saved game and
    // is used to undo move
    private static void reload(){
        List<Move> moves = historyOfMoves.listOfPreviousMoves();
        StateOfGame.variant.initializeStateOfGame();
        for(Move v : moves){
            StateOfGame.variant.changeState(v);
        }
    }

    //undo last move - make all previous moves, but last.
    public static void undoMove(){
        historyOfMoves.undoMove();
        reload();
    }

    public static StateOfGameplay colorOfLastMove(){
        Move v = historyOfMoves.lastMove();
        undoMove();
        StateOfGameplay ret = stateOfGameplay;
        if( v != null )
            StateOfGame.variant.changeState(v);
        return ret;
    }
}
