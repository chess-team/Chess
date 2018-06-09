package Chess.Model.Moves;

import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.ChessPieces.King;
import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

/*
For classes
 */
public abstract class SpecialMove extends Move {
    // to - target square

    @Override
    StateOfGameplay getMoveColor() {
        return StateOfGame.getStateOfGameplay();
    }

    @Override
    public String toString(){
        return "special move to " + to.toString();
    }

    public abstract void changeState();

    public abstract boolean isBreakingRules();

    boolean isKingTarget(){
        ChessPiece chessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(to);

        return (chessPiece instanceof King);
    }
    boolean isEmptySquareTarget(){
        ChessPiece chessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(to);

        return (chessPiece instanceof EmptySquare);
    }


}
