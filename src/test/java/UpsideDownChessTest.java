import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class UpsideDownChessTest {
    @Test
    void testInitializeState(){
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