package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    {label = 'R';}

    public Rook(ChessColour a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<>();
        int maxDistance = Math.max(StateOfGame.chessboard.getXWidth(),
                StateOfGame.chessboard.getYWidth());
        for(int i = -maxDistance; i <= maxDistance; ++i) {
            if(i == 0) continue;
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i,0));
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }

            v = new Move(getPosition(),
                    getPosition().translateByVector(0,i));
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }

        return resultList;
    }

    @Override
    public boolean isBreakingRules(Move move) {
        return (!(move.differenceOnXCoordinate() == 0 ||
                move.differenceOnYCoordinate() == 0));
    }
}
