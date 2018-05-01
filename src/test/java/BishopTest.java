import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void testBishopMovement(){
        TestUtil.makeEmptyClassicChessboard();


        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(7,7)));

        TestUtil.testMoveValidatorForClassicChess(new Position(0,0));
        TestUtil.testMoveValidatorForClassicChess(new Position(7,7));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(7,7));
        System.out.println(StateOfGame.chessboard);

        assertTrue(StateOfGame.chessboard.toString().equals(
                        ".......B\n" +
                        "......p.\n" +
                        ".....p..\n" +
                        "....p...\n" +
                        "...p....\n" +
                        "..p.....\n" +
                        ".p......\n" +
                        "p.......\n"));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(6,1)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(5,2)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(0,7)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(1,6)));


        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(6,3)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(7,4)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(4,1)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(3,0)));

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "b......B\n" +
                        ".b....p.\n" +
                        ".....p..\n" +
                        "....p..b\n" +
                        "...p..b.\n" +
                        "..p..B..\n" +
                        ".p..b.B.\n" +
                        "p..b....\n"));
        TestUtil.testMoveValidatorForClassicChess(new Position(5,2));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(5,2));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "b......B\n" +
                        ".p....p.\n" +
                        "..p..p..\n" +
                        "...pp..b\n" +
                        "...pp.p.\n" +
                        "..p..B..\n" +
                        ".p..p.B.\n" +
                        "p..b....\n"));
    }
}