package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.Moves.Move;

/*
Chess without check. Objective of game is to capture
enemy Chess.Model.ChessPieces.King.
   */
@SuppressWarnings("SpellCheckingInspection")
public class KillKingChess extends ClassicChess {

    // return true if move is correct.
    public boolean validateMove(Move move) {

        if(!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }

        ChessColour colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getChessColour();

        //noinspection RedundantIfStatement
        if(colorOfPlayer == colorOfLastMovedPiece){
            return false;
        }

        return true;
    }

    @Override
    public void inCaseOfEndOfGame(){
        if(ChessUtil.getKingPosition(ChessColour.BLACK) == null){
            StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_WON;
            return;
        }
        if(ChessUtil.getKingPosition(ChessColour.WHITE) == null){
            StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_WON;
            return;
        }

        ChessColour colorOfPossibleLoser = ChessUtil.
                getOtherColor(colorOfLastMovedPiece);

        drawRuleNoPossibleMove(colorOfPossibleLoser);
        drawRule3TimesSamePosition();
    }
}
