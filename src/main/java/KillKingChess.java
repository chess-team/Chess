/*
Chess without en passant, castling, check. Objective of game is to capture
enemy King.
   */
@SuppressWarnings("SpellCheckingInspection")
public class KillKingChess extends ClassicChess {

    // return true if move is correct.
    public boolean validateMove(Move move) {

        if(!isInsideBoard(move)) {
            return false;
        }

        if(!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }

        Color colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getColor();

        //noinspection RedundantIfStatement
        if(colorOfPlayer == colorOfLastMovedPiece){
            return false;
        }

        return true;
    }

    void changeStateOfOtherClasses(Move change){
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);
    }

    boolean checkIfGameEnded(){
        if(ChessUtil.getKingPosition(Color.BLACK) == null){
            StateOfGame.whichPlayerHaveMove = State.WHITE_WON;
            return true;
        }
        if(ChessUtil.getKingPosition(Color.WHITE) == null){
            StateOfGame.whichPlayerHaveMove = State.BLACK_WON;
            return true;
        }
        return false;
    }

    @Override
    public void changeState(Move change) {
        changeStateOfOtherClasses(change);
        if(checkIfGameEnded()){
            return;
        }
        swapColor();
        switch(colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.whichPlayerHaveMove = State.WHITE_MOVE;
                break;
            case WHITE:
                StateOfGame.whichPlayerHaveMove = State.BLACK_MOVE;
                break;
        }
    }
}
