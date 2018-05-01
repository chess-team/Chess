import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class DunsanysChessTest {
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void testInitializeState(){
        StateOfGame.variant = new DunsanysChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "PPPPPPPP\n" +
                        "PPPPPPPP\n" +
                        "PPPPPPPP\n" +
                        "PPPPPPPP\n"));

    }
}