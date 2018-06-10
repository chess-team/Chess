package Chess.Model.Moves;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

public class KillFigureMove extends SpecialMove{
    public KillFigureMove(Position to) {
        this.to = to;
    }

    @Override
    public String toString(){
        return "kill figure at " + to.toString();
    }

    @Override
    public void changeState(){
        StateOfGame.chessboard.setFigure(new EmptySquare(to));
    }

    @Override
    public boolean isBreakingRules(){
        return isKingTarget() || isEmptySquareTarget();
    }
}
