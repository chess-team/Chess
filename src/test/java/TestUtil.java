import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Class hold set of function that is used in multiple tests.
 */
class TestUtil {
    public static void makeEmptyClassicChessboard(){
        //initialize new state of game
        StateOfGame.variant = new ClassicChess();

        //used to make chessboard empty
        StateOfGame.chessboard = new ClassicChessboard();
    }

    public static void setPawnsWhereChessPieceCanMove(
            Position positionOfChessPiece){
        for(Move v : StateOfGame.chessboard.getChessPieceOnPosition(
                positionOfChessPiece).listOfPossibleMoves()){
            StateOfGame.chessboard.setFigure(
                    new Pawn(Color.BLACK, v.to));

        }
    }

    // test for Classic chess if list of possible move is consistent with
    // validateMove method.
    public static void testMoveValidatorForClassicChess(
            Position positionOfChessPiece){
        int numberOfMovesFromPosition = 0;
        for(int i = -8; i <= 8; ++i) {
            for(int j = -8; j <= 8; ++j) {
                if(StateOfGame.variant.validateMove(
                        new Move(positionOfChessPiece,
                                positionOfChessPiece.translateByVector(i,j)))) {
                    ++numberOfMovesFromPosition;
                }
            }
        }
        System.out.println(numberOfMovesFromPosition);
        System.out.println(StateOfGame.chessboard.getChessPieceOnPosition(
                    positionOfChessPiece).listOfPossibleMoves().size());
        assertTrue(numberOfMovesFromPosition ==
                StateOfGame.chessboard.getChessPieceOnPosition(
                positionOfChessPiece).listOfPossibleMoves().size());


    }
}
