package Chess.Model.Moves;

import Chess.Model.ChessColour;

import java.util.ArrayList;

/*
Class that holds list of possible special moves for player.
 */
public interface SpecialMoves {
    ArrayList<Move> listOfPossibleMoves(ChessColour playerColor);
}
