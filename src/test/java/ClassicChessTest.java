import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("SpellCheckingInspection")
class ClassicChessTest {
    @Test
    void testInitializeState(){
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

    }
}