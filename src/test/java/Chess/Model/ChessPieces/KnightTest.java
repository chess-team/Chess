package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.Knight;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    // Test of Chess.Model.ChessPieces.Knight movement in classic chess
    @Test
    public void testKnightMovement(){
        TestUtil.makeEmptyClassicChessboard();

        StateOfGame.chessboard.setFigure(
                new Knight(ChessColour.WHITE, new Position(0,0)));


        StateOfGame.chessboard.setFigure(
                new Knight(ChessColour.WHITE, new Position(4,4)));

        StateOfGame.chessboard.setFigure(
                new Knight(ChessColour.WHITE, new Position(5,6)));

        StateOfGame.chessboard.setFigure(
                new Knight(ChessColour.BLACK, new Position(3,6)));

        assertTrue(StateOfGame.chessboard.getChessPieceOnPosition(
                new Position(4,4)).listOfPossibleMoves().size() == 7);

        assertTrue(StateOfGame.chessboard.getChessPieceOnPosition(
                new Position(0,0)).listOfPossibleMoves().size() == 2);

        System.out.println(StateOfGame.chessboard);
        TestUtil.testMoveValidatorForClassicChess(new Position(0,0));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(0,0));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "...k.K..\n" +
                        "........\n" +
                        "....K...\n" +
                        "........\n" +
                        ".p......\n" +
                        "..p.....\n" +
                        "K.......\n"));

        TestUtil.testMoveValidatorForClassicChess(new Position(4,4));
        TestUtil.setPawnsWhereChessPieceCanMove(new Position(4,4));
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "...p.K..\n" +
                        "..p...p.\n" +
                        "....K...\n" +
                        "..p...p.\n" +
                        ".p.p.p..\n" +
                        "..p.....\n" +
                        "K.......\n"));

    }
}