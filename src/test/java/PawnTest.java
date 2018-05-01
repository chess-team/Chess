import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    // Test of Pawn movement in classic chess
    @Test
    void testPawnMovement(){
        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(0,1)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(2,1)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(6,1)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(7,1)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(5,2)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(7,4)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(1,4)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(0,6)));

        StateOfGame.chessboard.setFigure(
                new Pawn(Color.WHITE, new Position(4,6)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(0,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(1,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(2,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(6,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(7,5)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(3,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(4,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(7,2)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(7,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(6,2)));

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "...rr...\n" +
                        "P...P...\n" +
                        "rrr...rr\n" +
                        ".P.....P\n" +
                        ".......r\n" +
                        ".....Prr\n" +
                        "P.P...PP\n" +
                        "........\n"));

        TestUtil.setPawnsWhereChessPieceCanMove( new Position(0,1));
        TestUtil.setPawnsWhereChessPieceCanMove( new Position(2,1));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(6,1));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(7,1));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(5,2));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(7,4));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(1,4));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(0,6));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "...rr...\n" +
                        "P...P...\n" +
                        "prp...pr\n" +
                        ".P.....P\n" +
                        "p.p..p.r\n" +
                        "p.p..Ppp\n" +
                        "P.P...PP\n" +
                        "........\n"));
    }
}