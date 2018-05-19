package Chess.Model.GameVariants;

import Chess.Model.ChessPieces.Pawn;
import Chess.Model.Moves.Move;
import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

/*
Chess with two moves in each turn. Objective is to kill enemy king.
There is not check, checkmate or en passant move.
First turn is single white move.
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class DoubleMoveChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 1;

    @Override
    public void initializeStateOfGame() {
        numberOfConsecutiveSameColorMoves = 1;
        Pawn.enPassantDisabled = true;
        super.initializeStateOfGame();
    }

    @Override
    public void changeState(Move change) {

        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);
        changeStateOfGameplay();
    }

    @Override
    protected void changeStateOfGameplay() {
        ++numberOfConsecutiveSameColorMoves;
        if(numberOfConsecutiveSameColorMoves == 2) {
            numberOfConsecutiveSameColorMoves = 0;
            swapColor();
            swapPlayerColor();
        }
        inCaseOfEndOfGame();
    }
}
