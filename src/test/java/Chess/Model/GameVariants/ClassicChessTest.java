package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.King;
import Chess.Model.ChessPieces.Rook;
import Chess.Model.GameVariants.ClassicChess;
import Chess.Model.Moves.Castling;
import Chess.Model.Moves.Move;
import Chess.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("SpellCheckingInspection")
public class ClassicChessTest {
    @Test
    public void testInitializeState(){
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

    private void setEndgameState(){
        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new King(ChessColour.WHITE, new Position(7,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(1,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(7,6)));

        StateOfGame.chessboard.setFigure(
                new King(ChessColour.BLACK, new Position(0,0)));

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
    public void testWin(){
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

        assertTrue(ChessUtil.getPositionsThatAttacksTheKing(
                new Position(0,0)).size() == 1);
        assertTrue(StateOfGame.stateOfGameplay == StateOfGameplay.WHITE_WON);

    }

    @Test
    public void testDraw(){
        setEndgameState();
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
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
    public void testCastling(){
        TestUtil.makeEmptyClassicChessboard();

        StateOfGame.chessboard.setFigure(
                new King(ChessColour.WHITE, new Position(4,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(7,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(0,0)));
        System.out.println(StateOfGame.chessboard.
                getChessPieceOnPosition(new Position(4,0)).listOfPossibleMoves());
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
                new Rook(ChessColour.BLACK, new Position(6,7)));

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

    @Test
    void testUndoMove(){
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
        StateOfGame.variant.changeState(new Move (
                new Position(0,1), new Position(0 ,2)));

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "P.......\n" +
                        ".PPPPPPP\n" +
                        "RKBQWBKR\n"));
        StateOfGame.undoMove();
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
        assertTrue(StateOfGame.variant.validateMove(new Move (
                new Position(0,1), new Position(0 ,2))));

    }

    @Test
    void testListOfCapturedPieces(){
        StateOfGame.variant = new ClassicChess();
        StateOfGame.variant.initializeStateOfGame();
        System.out.println(StateOfGame.chessboard);

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(0,2)));

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "pppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "R.......\n" +
                        "PPPPPPPP\n" +
                        "RKBQWBKR\n"));
        StateOfGame.variant.changeState(new Move (
                new Position(0,2), new Position(0 ,6)));

        assertTrue(StateOfGame.chessboard.toString().equals(
                        "rkbqwbkr\n" +
                        "Rppppppp\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "PPPPPPPP\n" +
                        "RKBQWBKR\n"));

        System.out.println(StateOfGame.capturedPieces);
        assertTrue(StateOfGame.capturedPieces.contains('p'));
        assertTrue(StateOfGame.capturedPieces.size() == 1);

    }
}