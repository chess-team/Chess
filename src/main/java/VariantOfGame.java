
/*
Class that holds information about variant of game also class validate if
move(change of game state) is correct.
 */

public interface VariantOfGame {
    boolean validateMove(Move move);
    void initializeStateOfGame();

    // method changes state after player move.
    void changeState(Move change);
}
