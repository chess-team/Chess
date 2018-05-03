package Chess.Model.GameVariants;

import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

class TranscendentalChessTest {

    @Test
    void initializeStateOfGame() {
        StateOfGame.variant = new TranscendentalChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }
}