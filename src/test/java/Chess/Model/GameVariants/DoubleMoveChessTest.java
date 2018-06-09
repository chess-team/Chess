package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.Moves.Move;
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
                if(j == 0)continue;
                assertTrue(doubleMove.validateMove(new Move(
                        new Position(j, 1), new Position(j, 3))));

                doubleMove.changeState(new Move(
                        new Position(j, 1), new Position(j, 3)));
            }
            System.out.println(ChessUtil.listOfAllMoves(ChessColour.BLACK));
            for(int j = i; j <= i + 1; ++j) {
                assertTrue(doubleMove.validateMove(new Move(
                        new Position(j, 6), new Position(j, 4))));

                doubleMove.changeState(new Move(
                        new Position(j, 6), new Position(j, 4)));
            }

        }
    }
}