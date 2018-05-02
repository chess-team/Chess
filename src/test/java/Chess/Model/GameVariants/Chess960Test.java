package Chess.Model.GameVariants;

import Chess.Model.GameVariants.Chess960;
import Chess.Model.StateOfGame;
import org.junit.jupiter.api.Test;

public class Chess960Test {
    @Test
    public void test(){
        StateOfGame.variant = new Chess960();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }
}