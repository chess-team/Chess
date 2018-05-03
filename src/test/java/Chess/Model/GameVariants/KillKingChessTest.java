package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

class KillKingChessTest {

    @Test
    void validateMove() {
        StateOfGame.variant = new ProgressiveChessTakeAll();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }

    @Test
    void inCaseOfEndOfGame() {
        StateOfGame.variant = new ProgressiveChessTakeAll();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }
}