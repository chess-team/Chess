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
        switch (stateOfGameplay){
            case DRAW:
            case WHITE_WON:
            case BLACK_WON:
                return stateOfGameplay;
        }
        switch (((ClassicChess) variant).colorOfLastMovedPiece){
            case BLACK:
                return StateOfGameplay.WHITE_MOVE;
            case WHITE:
                return StateOfGameplay.BLACK_MOVE;
        }
        return StateOfGameplay.WHITE_MOVE;
    }
}
