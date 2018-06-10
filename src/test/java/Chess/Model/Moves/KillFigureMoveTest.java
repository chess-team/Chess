package Chess.Model.Moves;

import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KillFigureMoveTest {

    @Test
    void undoMove() {

    }

    @Test
    void changeState() {
        StateOfGame.variant = new ClassicChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "PPPPPPPP\n" +
                        "RKBQWBKR\n"));
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(0, 7))));
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(0, 1))));
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(0, 0))));
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(3, 7))));
        StateOfGame.variant.
                changeState(new KillFigureMove(new Position(3, 7)));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(5, 7))));
        StateOfGame.variant.
                changeState(new KillFigureMove(new Position(5, 7)));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(4, 1))));
        StateOfGame.variant.
                changeState(new KillFigureMove(new Position(4, 1)));
        System.out.println(StateOfGame.chessboard);
        System.out.println(StateOfGame.stateOfGameplay);
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(6, 7))));
        StateOfGame.variant.
                changeState(new KillFigureMove(new Position(6, 7)));
        StateOfGame.variant.
                changeState(new Move(new Position(3, 0), new Position(4,1)));
        System.out.println(StateOfGame.chessboard);
        System.out.println(StateOfGame.stateOfGameplay);
        assertFalse(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(4, 6))));
        StateOfGame.variant.
                changeState(new Move(new Position(7, 7), new Position(5,7)));
        System.out.println(StateOfGame.chessboard);
        System.out.println(StateOfGame.stateOfGameplay);
        assertTrue(StateOfGame.variant.
                validateMove(new KillFigureMove(new Position(4, 6))));
        StateOfGame.variant.
                changeState(new KillFigureMove(new Position(4, 6)));

    }

    @Test
    void isBreakingRules() {
    }
}