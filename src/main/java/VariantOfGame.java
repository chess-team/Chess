
/*
Class that holds information about variant of game also class validate if
move(change of game state) is correct.
 */

@SuppressWarnings("unused")
public interface VariantOfGame {
    boolean validateMove(Move move);
    void initializeStateOfGame();

    // changes state after player move.
    void changeState(Move change);

    // returns instance of class implementing SpecialMoves
    SpecialMoves getSpecialMoves();
}
