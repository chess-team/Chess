package Chess.Model;
/*
Class that holds information about variant of game also class validate if
move(change of game state) is correct.
 */

import Chess.Model.Moves.Move;
import Chess.Model.Moves.SpecialMoves;

public interface VariantOfGame {
    boolean validateMove(Move move);

    void initializeStateOfGame();

    // changes state after player move.
    void changeState(Move change);

    // returns instance of class implementing Chess.Model.Moves.SpecialMoves
    SpecialMoves getSpecialMoves();
}
