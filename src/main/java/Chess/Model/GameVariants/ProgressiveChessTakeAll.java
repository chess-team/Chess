package Chess.Model.GameVariants;


import Chess.Model.*;

/*
Player make number of consecutive moves. In each turn longer by 1.
Objective of game is to capture all enemy figures.
 */
public class ProgressiveChessTakeAll extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;
    private int numberOfMovesInTurn = 1;

    @Override
    public void initializeStateOfGame() {
        numberOfConsecutiveSameColorMoves = 0;
        numberOfMovesInTurn = 1;
        super.initializeStateOfGame();
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
    }

    @Override
    void inCaseOfEndOfGame() {
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.WHITE) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_WON;
            return;
        }
        if(ChessUtil.getNumberOfPiecesGivenColor(ChessColour.BLACK) == 0){
            StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_WON;
            return;
        }

        ChessColour colorOfPossibleLoser = ChessColour.WHITE;
        if(StateOfGame.stateOfGameplay == StateOfGameplay.BLACK_MOVE){
            colorOfPossibleLoser = ChessColour.BLACK;
        }

        drawRuleNoPossibleMove(colorOfPossibleLoser);
        drawRule3TimesSamePosition();
    }

    @Override
    protected void changeStateOfGameplay() {
        ++numberOfConsecutiveSameColorMoves;
        if(numberOfConsecutiveSameColorMoves == numberOfMovesInTurn) {
            numberOfConsecutiveSameColorMoves = 0;
            swapColor();
            swapPlayerColor();
            ++numberOfMovesInTurn;
        }
        inCaseOfEndOfGame();
    }
}
