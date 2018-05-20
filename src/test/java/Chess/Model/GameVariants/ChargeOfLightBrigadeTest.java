package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargeOfLightBrigadeTest {

    @Test
    void testInitializeStateOfGame() {
        StateOfGame.variant = new ChargeOfLightBrigade();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "kkkkwkkk\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "PPPPPPPP\n" +
                        ".Q.QW.Q.\n"));
    }
}