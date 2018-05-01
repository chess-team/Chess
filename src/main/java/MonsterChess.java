public class MonsterChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(
                        4,0)));
        for(int i = 2; i < 6; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(Color.WHITE, new Position(i,1)));
        }
        setLineOfPawns(6,Color.BLACK);
        setLineOfFigures(7, Color.BLACK);
    }

    @Override
    public void changeState(Move change) {
        changeStateOfOtherClasses(change);
        if(checkIfGameEnded()){
            return;
        }
        switch(colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.whichPlayerHaveMove = State.WHITE_MOVE;
                swapColor();
                numberOfConsecutiveSameColorMoves = 0;
                break;
            case WHITE:
                if(numberOfConsecutiveSameColorMoves == 1){
                    StateOfGame.whichPlayerHaveMove = State.BLACK_MOVE;
                    swapColor();
                    numberOfConsecutiveSameColorMoves = 0;
                }
                ++numberOfConsecutiveSameColorMoves;
                break;
        }
    }
}
