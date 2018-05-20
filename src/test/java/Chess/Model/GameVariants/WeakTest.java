package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeakTest {
    @Test
    public void testInitializeState(){
        StateOfGame.variant = new Weak();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "kkkkwkkk\n" +
                        "pppppppp\n" +
                        "..p..p..\n" +
                        ".pppppp.\n" +
                        "........\n" +
                        "........\n" +
                        "PPPPPPPP\n" +
                        "RKBQWBKR\n"));

    }
}