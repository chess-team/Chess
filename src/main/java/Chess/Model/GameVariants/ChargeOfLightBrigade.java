package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.ChessPieces.Knight;
import Chess.Model.ChessPieces.Queen;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

/*
Next chess variant. White has 3 queens, king and line of pawns.
Black has line of pawns, king and 7 knights.
 */
public class ChargeOfLightBrigade extends ClassicChess{
    @Override
    public void initializeStateOfGame() {
        super.initializeStateOfGame();
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i) {
            if(i == 4){
                continue;
            }
            StateOfGame.chessboard.setFigure(
                    new Knight(ChessColour.BLACK, new Position(i, 7)));
        }
        for(int i = 0; i < 8; ++i) {
            if(i == 4){
                continue;
            }
            if(i == 1 || i == 3 || i == 6) {
                StateOfGame.chessboard.setFigure(
                        new Queen(ChessColour.WHITE, new Position(i, 0)));
            }
            else {
                StateOfGame.chessboard.setFigure(
                        new EmptySquare(new Position(i, 0)));
            }
        }
    }
}
