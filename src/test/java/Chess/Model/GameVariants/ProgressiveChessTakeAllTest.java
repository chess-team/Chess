package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

class ProgressiveChessTakeAllTest {

    @Test
    void inCaseOfEndOfGame() {
        StateOfGame.variant = new ProgressiveChessTakeAll();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }

    @Test
    void changeState() {
        StateOfGame.variant = new ProgressiveChessTakeAll();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }
}