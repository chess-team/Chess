package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class King extends ChessPiece {

    {
        label = 'W';
    }

    public King(ChessColour a, Position b) {
        super(a, b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList<Move> resultList = new ArrayList<>();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) continue;
                Move v = new Move(getPosition(),
                        getPosition().translateByVector(i, j));
                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }
            }
        }
        int x = StateOfGame.chessboard.getXWidth();
        for (int i = -x; i <= x; ++i) {
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i, 0));
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }
        return resultList;
    }

    @Override
    public boolean isBreakingRules(Move move) {
        return (!(move.differenceOnXCoordinate() <= 1 &&
                move.differenceOnYCoordinate() <= 1));
    }
}
