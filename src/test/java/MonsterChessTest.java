import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class MonsterChessTest {
    @Test
    void testInitializeState(){
        StateOfGame.variant = new MonsterChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "..PPPP..\n" +
                        "....W...\n"));

    }
}