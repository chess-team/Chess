package Chess.Model.ArtificialIntelligence;

import Chess.Model.ChessColour;
import Chess.Model.ChessUtil;
import Chess.Model.Moves.Move;
import Chess.Model.StateOfGame;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*
AI making random moves
 */
public class RandomAI implements AI{
    @Override
    public Move getAIMove() {
        switch (StateOfGame.getStateOfGameplay()){
            case WHITE_MOVE:
                List<Move> tempList = ChessUtil.listOfAllMoves(ChessColour.WHITE);
                int n = tempList.size();
                int randomNumber = ThreadLocalRandom.current().nextInt(0, n);
                return tempList.get(randomNumber);
            case BLACK_MOVE:
                tempList = ChessUtil.listOfAllMoves(ChessColour.BLACK);
                n = tempList.size();
                randomNumber = ThreadLocalRandom.current().nextInt(0, n);
                return tempList.get(randomNumber);
        }
        return null;
    }
}
