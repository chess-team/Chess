package Chess.Model.Moves;


import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

//blocks figure for three next turns
public class BlockFigureMove extends SpecialMove{
    public BlockFigureMove(Position to) {
        this.to = to;
    }

    @Override
    public String toString(){
        return "block figure at " + to.toString();
    }

    @Override
    public void changeState() {
        ChessPiece chessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(to);
        chessPiece.setPosition(to);
        chessPiece.whenBlocked = StateOfGame.historyOfMoves.listOfPreviousMoves().size();
        StateOfGame.chessboard.setFigure(chessPiece);
    }

    @Override
    public boolean isBreakingRules() {
        return isEmptySquareTarget();
    }


}
