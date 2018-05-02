package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ClassicChessboard;
import Chess.Model.StateOfGame;

/*
Chess that have different starting position that normal chess. White
Chess pieces are swapped with black ones. (So pawns are one step from
promotion).
 */
public class UpsideDownChess extends ClassicChess{

    @Override
    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(6, ChessColour.WHITE);
        setLineOfPawns(1, ChessColour.BLACK);
        setLineOfFigures(7, ChessColour.WHITE);
        setLineOfFigures(0, ChessColour.BLACK);
    }
}
