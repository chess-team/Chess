package Chess.Model.GameVariants;


import Chess.Model.*;
import Chess.Model.Moves.Move;
/*
Player make number of consecutive moves. In each turn longer by 1.
Objective of game is to capture all enemy figures.
 */
public class ProgressiveChessTakeAll extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;
    private int numberOfMovesInTurn = 1;

    @Override
    public void inCaseOfEndOfGame() {
        super.inCaseOfEndOfGame();
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.WHITE) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_WON;
            return;
        }
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.BLACK) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_WON;
            return;
        }
        drawRule3TimesSamePosition();
        ChessColour colorOfPossibleLoser = ChessUtil.
                getOtherColor(colorOfLastMovedPiece);

        drawRuleNoPossibleMove(colorOfPossibleLoser);
    }

    //TODO tests
    @Override
    public void changeState(Move change) {
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
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);
        inCaseOfEndOfGame();
    }
}
