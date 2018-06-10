package Chess.Model.Moves;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.King;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

public class SetFigureMove extends SpecialMove{
    // promoteTo - mark which figure set.
    public SetFigureMove(Position to, ChessPiece chosenChessPiece) {
        this.to = to;
        ChessPiece t = chosenChessPiece;
        try {
            t = chosenChessPiece.getClass().getConstructor(ChessColour.class, Position.class).
                    newInstance(chosenChessPiece.getChessColour(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.setPosition(to);
        this.promoteTo = t;
        isPromotion = true;
    }

    @Override
    public String toString(){
        return "set figure" + promoteTo.toString() + "to " + promoteTo.getPosition().toString();
    }

    @Override
    public void changeState(){
        // deep copy of promoteTo
        ChessPiece t = promoteTo;
        try {
            t = promoteTo.getClass().getConstructor(ChessColour.class, Position.class).
                    newInstance(promoteTo.getChessColour(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.setPosition(to);
        StateOfGame.chessboard.setFigure(t);
    }

    @Override
    public boolean isBreakingRules(){
        return (isKingTarget());
    }
}