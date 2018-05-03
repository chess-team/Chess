package Chess.Model.GameVariants;

import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.Model.VariantOfGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleMoveChessTest {

    @Test
    void changeState() {
        VariantOfGame doubleMove = new DoubleMoveChess();
        StateOfGame.variant = doubleMove;
        doubleMove.initializeStateOfGame();

        for(int i = 0; i < 8; i += 2) {
            for(int j = i; j <= i + 1; ++j) {
                assertTrue(doubleMove.validateMove(new Move(
                        new Position(j, 1), new Position(j, 3))));

                doubleMove.changeState(new Move(
                        new Position(j, 1), new Position(j, 3)));
            }
            for(int j = i; j <= i + 1; ++j) {
                assertTrue(doubleMove.validateMove(new Move(
                        new Position(j, 7), new Position(j, 5))));

                doubleMove.changeState(new Move(
                        new Position(j, 7), new Position(j, 5)));
            }

        }

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "........\n" +
                        "........\n" +
                        "pppppppp\n" +
                        "PPPPPPPP\n" +
                        "........\n" +
                        "........\n" +
                        "RKBQWBKR\n"));
    }
}