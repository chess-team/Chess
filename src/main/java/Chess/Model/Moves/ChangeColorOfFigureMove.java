package Chess.Model.Moves;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

public class ChangeColorOfFigureMove extends SpecialMove{
    public ChangeColorOfFigureMove(Position to) {
        this.to = to;
    }

    @Override
    public String toString(){
        return "change color of figure at " + to.toString();
    }

    @Override
    public void changeState(){
        ChessPiece chessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(to);
        ChessColour color = chessPiece.getChessColour();
        ChessColour differentColor = ChessColour.WHITE;
        if(color == differentColor){
            differentColor = ChessColour.BLACK;
        }
        color = differentColor;
        chessPiece.setColor(color);
        chessPiece.setPosition(to);
        StateOfGame.chessboard.setFigure(chessPiece);
    }

    @Override
    public boolean isBreakingRules(){
        return (isKingTarget() || isEmptySquareTarget());
    }
}
