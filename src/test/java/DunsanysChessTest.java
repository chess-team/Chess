import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DunsanysChessTest {
    @Test
    void testInitializeState(){
        TestUtil.makeEmptyClassicChessboard();
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