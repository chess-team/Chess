import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class RookTest {

    @Test
    void testRookMovement(){
        TestUtil.makeEmptyClassicChessboard();


        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(0,0)));

        TestUtil.testMoveValidatorForClassicChess(new Position(0,0));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(0,0));
        System.out.println(StateOfGame.chessboard);

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "p.......\n" +
                        "p.......\n" +
                        "p.......\n" +
                        "p.......\n" +
                        "p.......\n" +
                        "p.......\n" +
                        "p.......\n" +
                        "Rppppppp\n"));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(7,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(3,3)));

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
                        "p..R....\n" +
                        "p..p....\n" +
                        "p..p....\n" +
                        "rppRppR.\n" +
                        "p..p....\n" +
                        "p..r....\n" +
                        "Rpprpppp\n"));
    }
}