import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void testQueenMovement1(){
        TestUtil.makeEmptyClassicChessboard();


        StateOfGame.chessboard.setFigure(
                new Queen(Color.WHITE, new Position(7,7)));

        TestUtil.testMoveValidatorForClassicChess(new Position(7,7));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(7,7));
        System.out.println(StateOfGame.chessboard);

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "pppppppQ\n" +
                        "......pp\n" +
                        ".....p.p\n" +
                        "....p..p\n" +
                        "...p...p\n" +
                        "..p....p\n" +
                        ".p.....p\n" +
                        "p......p\n"));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(6,1)));

        StateOfGame.chessboard.setFigure(
                new Queen(Color.WHITE, new Position(5,2)));

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
                        "bppppppQ\n" +
                        ".b....pp\n" +
                        ".....p.p\n" +
                        "....p..b\n" +
                        "...p..bp\n" +
                        "..p..Q.p\n" +
                        ".p..b.Bp\n" +
                        "p..b...p\n"));

        TestUtil.testMoveValidatorForClassicChess(new Position(5,2));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(5,2));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "bppppppQ\n" +
                        ".p....pp\n" +
                        "..p..p.p\n" +
                        "...ppp.b\n" +
                        "...ppppp\n" +
                        "..pppQpp\n" +
                        ".p..ppBp\n" +
                        "p..b.p.p\n"));
    }


    @Test
    void testQueenMovement2(){
        TestUtil.makeEmptyClassicChessboard();


        StateOfGame.chessboard.setFigure(
                new Queen(Color.WHITE, new Position(0,0)));

        TestUtil.testMoveValidatorForClassicChess(new Position(0,0));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(0,0));
        System.out.println(StateOfGame.chessboard);

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "p......p\n" +
                        "p.....p.\n" +
                        "p....p..\n" +
                        "p...p...\n" +
                        "p..p....\n" +
                        "p.p.....\n" +
                        "pp......\n" +
                        "Qppppppp\n"));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(7,7)));

        StateOfGame.chessboard.setFigure(
                new Queen(Color.WHITE, new Position(3,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(6,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(3,6)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(3,6)));

        System.out.println(StateOfGame.chessboard);

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(3,2)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(3,1)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(3,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(0,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(1,3)));

        System.out.println(StateOfGame.chessboard);

        TestUtil.testMoveValidatorForClassicChess(new Position(3,3));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(3,3));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "p......R\n" +
                        "p..R..p.\n" +
                        "pp.p.p..\n" +
                        "p.ppp...\n" +
                        "rppQppR.\n" +
                        "p.ppp...\n" +
                        "pp.r.p..\n" +
                        "Qpprpppp\n"));
    }
}