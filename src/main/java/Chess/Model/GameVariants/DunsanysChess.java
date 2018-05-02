package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ClassicChessboard;
import Chess.Model.StateOfGame;

@SuppressWarnings("ALL")
public class DunsanysChess extends KillKingChess{

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(0, ChessColour.WHITE);
        setLineOfPawns(1, ChessColour.WHITE);
        setLineOfPawns(2, ChessColour.WHITE);
        setLineOfPawns(3, ChessColour.WHITE);

        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(7, ChessColour.BLACK);
    }
}
