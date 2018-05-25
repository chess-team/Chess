package Chess.Model.GameVariants;

import Chess.Model.ChessPieces.Pawn;
import Chess.Model.Moves.Castling;
import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

/*
Chess with two moves in each turn. Objective is to kill enemy king.
There is no check, checkmate, castling or en passant move.
First turn is single white move.
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class DoubleMoveChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 1;

    @Override
    public void initializeStateOfGame() {
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
        StateOfGame.historyOfMoves.clear();
        numberOfConsecutiveSameColorMoves = 1;
        Pawn.enPassantDisabled = true;
        Castling.castlingDisabled = true;
        super.initializeStateOfGame();
    }

    @Override
    protected void changeStateOfGameplay() {
        ++numberOfConsecutiveSameColorMoves;
        if (numberOfConsecutiveSameColorMoves == 2) {
            numberOfConsecutiveSameColorMoves = 0;
            swapColor();
            swapPlayerColor();
        }
        inCaseOfEndOfGame();
    }
}
