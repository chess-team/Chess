/*
Chess with two moves in each turn.
 */
public class DoublemoveChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 1;

    @Override
    public void changeState(Move change) {
        changeStateOfOtherClasses(change);
        if(checkIfGameEnded()){
            return;
        }
        switch(colorOfLastMovedPiece){
            case BLACK:
                ++numberOfConsecutiveSameColorMoves;
                if(numberOfConsecutiveSameColorMoves == 0){
                    swapColor();
                }
                if(numberOfConsecutiveSameColorMoves == 2){
                    StateOfGame.whichPlayerHaveMove = State.WHITE_MOVE;
                    numberOfConsecutiveSameColorMoves = 0;
                }
                break;
            case WHITE:
                ++numberOfConsecutiveSameColorMoves;
                if(numberOfConsecutiveSameColorMoves == 0){
                    swapColor();
                }
                if(numberOfConsecutiveSameColorMoves == 2){
                    StateOfGame.whichPlayerHaveMove = State.BLACK_MOVE;
                    numberOfConsecutiveSameColorMoves = 0;
                }

                break;
        }
    }
}
