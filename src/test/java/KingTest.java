import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    @Test
    void testKingProtection(){
        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(6,2)));

        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(7,2)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(7,0)));

        StateOfGame.chessboard.setFigure(
                new King(Color.BLACK, new Position(6,0)));

        assertTrue(StateOfGame.chessboard.getChessPieceOnPosition(
                new Position(7,2)).listOfPossibleMoves().size() == 1);

        TestUtil.setPawnsWhereChessPieceCanMove(new Position(6,2));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "......BW\n" +
                        ".......p\n" +
                        "......wr\n"));

        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(1,4)));

        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(1,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(1,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(2,2)));

        StateOfGame.chessboard.setFigure(
                new King(Color.BLACK, new Position(1,2)));

        TestUtil.setPawnsWhereChessPieceCanMove(new Position(1,4));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(1,5));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "pp......\n" +
                        "pW......\n" +
                        "pB......\n" +
                        ".r......\n" +
                        ".wr.....\n" +
                        "........\n" +
                        "........\n"));
    }
}