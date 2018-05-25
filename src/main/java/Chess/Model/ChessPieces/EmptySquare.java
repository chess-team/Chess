package Chess.Model.ChessPieces;

import Chess.Model.Moves.Move;
import Chess.Model.Position;

import java.util.ArrayList;
/*
Class uses null object pattern. Class represent that there is no figure
on some position. Class is also used to print empty square.
 */

public class EmptySquare extends ChessPiece {

    {
        label = '.';
    }

    public EmptySquare(Position b) {
        super(null, b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        return new ArrayList<>();
    }

    @Override
    public boolean isBreakingRules(Move move) {
        return true;
    }
}
