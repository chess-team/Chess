
/*
Class that implements rules of Classic chess.
 */
public class ClassicChess implements VariantOfGame {

    // return true if move is correct.
    public boolean validateMove(Move move) {
        if(move.to.x < 0 || move.to.x >= 8)return false;
        if(move.to.y < 0 || move.to.y >= 8)return false;
        if(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof EmptySquare)
                return false;
        if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof Knight) && ChessUtil.isMovePassingThroughFigure(move)){
            return false;
        }
        return true;
    }

    public void initializeStateOfGame() {

    }

    public void changeState(Move change) {
        switch (StateOfGame.colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.colorOfLastMovedPiece = Color.WHITE;
                break;
            case WHITE:
                StateOfGame.colorOfLastMovedPiece = Color.BLACK;
                break;
        }
        StateOfGame.chessboard.moveFigure(change);
    }
}
