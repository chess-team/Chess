import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("SpellCheckingInspection")
class ClassicChessTest {
    @Test
    void testInitializeState(){
        StateOfGame.variant = new ClassicChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                                "rkbqwbkr\n" +
                                "pppppppp\n" +
                                "........\n" +
                                "........\n" +
                                "........\n" +
                                "........\n" +
                                "PPPPPPPP\n" +
                                "RKBQWBKR\n"));

    }

    void setEndgameState(){
        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(7,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(1,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(7,6)));

        StateOfGame.chessboard.setFigure(
                new King(Color.BLACK, new Position(0,0)));

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        ".R.....W\n" +
                        ".......R\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "w.......\n"));
    }

    @Test
    void testWin(){
        setEndgameState();
        StateOfGame.variant.changeState(
                new Move(new Position(7,6), new Position(0, 6)));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        ".R.....W\n" +
                        "R.......\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "w.......\n"));
        System.out.println(StateOfGame.stateOfGameplay);
        assertTrue(StateOfGame.stateOfGameplay == StateOfGameplay.WHITE_WON);
    }

    @Test
    void testDraw(){
        setEndgameState();
        StateOfGame.variant.changeState(
                new Move(new Position(7,6), new Position(7, 1)));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        ".R.....W\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        ".......R\n" +
                        "w.......\n"));
        System.out.println(StateOfGame.stateOfGameplay);
        assertTrue(StateOfGame.stateOfGameplay == StateOfGameplay.DRAW);
    }

    @Test
    void testCastling(){
        TestUtil.makeEmptyClassicChessboard();

        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(4,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(7,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(0,0)));

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "R...W..R\n"));

        assertTrue(StateOfGame.variant.validateMove(
                new Castling(new Position(7,0))));

        assertTrue(StateOfGame.variant.validateMove(
                new Castling(new Position(0,0))));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(6,7)));

        assertFalse(StateOfGame.variant.validateMove(
                new Castling(new Position(7,0))));

        StateOfGame.variant.changeState(
                new Castling(new Position(0,0)));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "......r.\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        ".WR....R\n"));
    }
}