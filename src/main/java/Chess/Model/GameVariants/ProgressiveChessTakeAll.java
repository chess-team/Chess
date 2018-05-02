package Chess.Model.GameVariants;/*
Player make number of consecutive moves. In each turn longer by 1.
Objective of game is to capture all enemy figures.
 */

import Chess.Model.*;
import Chess.Model.Moves.Move;

public class ProgressiveChessTakeAll extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;
    private int numberOfMovesInTurn = 1;

    @Override
    public boolean checkIfGameEnded() {
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.WHITE) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_WON;
            return true;
        }
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.BLACK) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_WON;
            return true;
        }
        return false;
    }

    @Override
    public void changeState(Move change) {
        changeStateOfOtherClasses(change);
        if(checkIfGameEnded()){
            return;
        }
        switch(colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
                if(numberOfConsecutiveSameColorMoves == 1){
                    StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_MOVE;
                    swapColor();
                    numberOfConsecutiveSameColorMoves = 0;
                }
                swapColor();
                ++numberOfConsecutiveSameColorMoves;
                break;
            case WHITE:
                if(numberOfConsecutiveSameColorMoves == 1){
                    StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_MOVE;
                    swapColor();
                    numberOfConsecutiveSameColorMoves = 0;
                }
                ++numberOfConsecutiveSameColorMoves;
                break;
        }
    }
}