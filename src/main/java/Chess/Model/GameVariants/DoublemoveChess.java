package Chess.Model.GameVariants;

import Chess.Model.Moves.Move;
import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

/*
Chess with two moves in each turn.
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class DoublemoveChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 1;

    @SuppressWarnings("unused")
    @Override
    public void changeState(Move change) {
        changeStateOfOtherClasses(change);
        if(checkIfGameEnded()){
            return;
        }
        switch(colorOfLastMovedPiece){
            case BLACK:
                ++numberOfConsecutiveSameColorMoves;
                if(numberOfConsecutiveSameColorMoves == 0){
                    swapColor();
                }
                if(numberOfConsecutiveSameColorMoves == 2){
                    StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
                    numberOfConsecutiveSameColorMoves = 0;
                }
                break;
            case WHITE:
                ++numberOfConsecutiveSameColorMoves;
                if(numberOfConsecutiveSameColorMoves == 0){
                    swapColor();
                }
                if(numberOfConsecutiveSameColorMoves == 2){
                    StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_MOVE;
                    numberOfConsecutiveSameColorMoves = 0;
                }

                break;
        }
    }
}
