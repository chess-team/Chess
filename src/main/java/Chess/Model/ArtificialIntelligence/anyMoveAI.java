package Chess.Model.ArtificialIntelligence;

import Chess.Model.ChessColour;
import Chess.Model.ChessUtil;
import Chess.Model.Moves.Move;
import Chess.Model.StateOfGame;

public class anyMoveAI implements AI{

    @Override
    public Move getAIMove() {
        switch (StateOfGame.stateOfGameplay){
            case WHITE_MOVE:
                return ChessUtil.listOfAllMoves(ChessColour.WHITE).get(0);
            case BLACK_MOVE:
                return ChessUtil.listOfAllMoves(ChessColour.BLACK).get(0);
        }
        return null;
    }
}
