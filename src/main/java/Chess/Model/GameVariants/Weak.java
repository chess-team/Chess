package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.Knight;
import Chess.Model.ChessPieces.Pawn;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

/*
Black has 16 pawns, 7 knights, 1 king, and white classical starting situation.
    */
public class Weak extends ClassicChess {

    @Override
    public void initializeStateOfGame() {
        super.initializeStateOfGame();
        for (int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i) {
            if (i == 4) {
                continue;
            }
            StateOfGame.chessboard.setFigure(
                    new Knight(ChessColour.BLACK, new Position(i, 7)));
        }
        StateOfGame.chessboard.setFigure(
                new Pawn(ChessColour.BLACK, new Position(2, 5)));

        StateOfGame.chessboard.setFigure(
                new Pawn(ChessColour.BLACK, new Position(5, 5)));
        for (int i = 1; i < 7; ++i) {
            StateOfGame.chessboard.setFigure(
                    new Pawn(ChessColour.BLACK, new Position(i, 4)));
        }
    }
}
