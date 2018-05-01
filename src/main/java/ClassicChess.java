
/*
Class that implements rules of Classic chess.
 */
public class ClassicChess extends VariantSimilarToClassicChess {

    protected Color colorOfLastMovedPiece = Color.BLACK;

    // return true if move is correct.
    public boolean validateMove(Move move) {

        if(!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }

        Color colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getColor();

        if(colorOfPlayer == colorOfLastMovedPiece){
            return false;
        }

        if(isKingUnderAttackAfterMove(colorOfPlayer, move)){
            return false;
        }

        return true;
    }

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(1,Color.WHITE);
        setLineOfPawns(6,Color.BLACK);
        setLineOfFigures(0, Color.WHITE);
        setLineOfFigures(7, Color.BLACK);
    }


    protected void swapColor(){
        switch (colorOfLastMovedPiece){
            case BLACK:
                colorOfLastMovedPiece = Color.WHITE;
                break;
            case WHITE:
                colorOfLastMovedPiece = Color.BLACK;
                break;
        }
    }


    public void changeState(Move change) {
        swapColor();
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);

    }
}
