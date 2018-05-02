package Chess.Model.Moves;

import Chess.Model.ChessColour;

import java.util.ArrayList;

public class NoSpecialMoves implements SpecialMoves{
    public ArrayList<Move> listOfPossibleMoves(ChessColour playerColor) {
        return new ArrayList<Move>();
    }
}
