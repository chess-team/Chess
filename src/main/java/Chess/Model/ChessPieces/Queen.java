package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    {
        label = 'Q';
    }

    public Queen(ChessColour a, Position b) {
        super(a, b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList<Move> resultList = new ArrayList<>();
        int maxDistance = Math.max(StateOfGame.chessboard.getXWidth(),
                StateOfGame.chessboard.getYWidth());
        for (int distance = 1; distance <= maxDistance; ++distance) {
            for (int x = -1; x <= 1; ++x) {
                for (int y = -1; y <= 1; ++y) {
                    if (x == 0 && y == 0) continue;
                    Move v = new Move(getPosition(), getPosition().
                            translateByVector(distance * x, distance * y));
                    if (StateOfGame.variant.validateMove(v)) {
                        resultList.add(v);
                    }
                }
            }
        }

        return resultList;
    }

    @Override
    public boolean isBreakingRules(Move move) {
        return (!(move.differenceOnXCoordinate() ==
                move.differenceOnYCoordinate() ||
                move.differenceOnXCoordinate() == 0 ||
                move.differenceOnYCoordinate() == 0));
    }
}
