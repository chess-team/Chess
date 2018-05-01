import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Chess960Test {
    @Test
    void test(){
        StateOfGame.variant = new Chess960();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
    }
}