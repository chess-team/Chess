package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import Chess.Model.GameVariants.UpsideDownChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
public class UpsideDownChessTest {
    @Test
    public void testInitializeState(){
        StateOfGame.variant = new UpsideDownChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "RKBQWBKR\n" +
                        "PPPPPPPP\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "pppppppp\n" +
                        "rkbqwbkr\n"));

    }
}