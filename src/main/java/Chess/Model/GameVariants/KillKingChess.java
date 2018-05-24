package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.Moves.Castling;
import Chess.Model.Moves.Move;

/*
Chess without check, checkmate, castling. Objective of game is to capture
enemy king.
   */
@SuppressWarnings("SpellCheckingInspection")
public class KillKingChess extends ClassicChess {

    @Override
    public void initializeStateOfGame() {
        super.initializeStateOfGame();
        ClassicChess.isCheck = false;
        Castling.castlingDisabled = true;
    }

    @Override
    boolean isKingUnderAttackAfterMove(ChessColour kingColor, Move v) {
        return false;
    }

    @Override
    void inCaseOfEndOfGame(){
        if(ChessUtil.getKingPosition(ChessColour.BLACK) == null){
            StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_WON;
            return;
        }
        if(ChessUtil.getKingPosition(ChessColour.WHITE) == null){
            StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_WON;
            return;
        }

        ChessColour colorOfPossibleLoser = ChessColour.WHITE;
        if(StateOfGame.stateOfGameplay == StateOfGameplay.BLACK_MOVE){
            colorOfPossibleLoser = ChessColour.BLACK;
        }

        drawRuleNoPossibleMove(colorOfPossibleLoser);
        drawRule3TimesSamePosition();
    }
}
