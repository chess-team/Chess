package Chess.Model.ArtificialIntelligence;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.Bishop;
import Chess.Model.ChessPieces.King;
import Chess.Model.ChessPieces.Rook;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

//TODO remove bug
public class AITest {
    public void testAI(AI player){
        TestUtil.makeEmptyClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new Bishop(ChessColour.WHITE, new Position(6,2)));

        StateOfGame.chessboard.setFigure(
                new King(ChessColour.WHITE, new Position(7,2)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.BLACK, new Position(7,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.BLACK, new Position(0,3)));

        StateOfGame.chessboard.setFigure(
                new Rook(ChessColour.WHITE, new Position(5,2)));

        StateOfGame.chessboard.setFigure(
                new King(ChessColour.BLACK, new Position(6,0)));

        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "r.......\n" +
                        ".....RBW\n" +
                        "........\n" +
                        "......wr\n"));
        StateOfGame.variant.changeState(player.getAIMove());
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "r.......\n" +
                        ".....R.W\n" +
                        ".......B\n" +
                        "......wr\n"));
        System.out.println(StateOfGame.stateOfGameplay);

        assertTrue(StateOfGame.variant.validateMove(
                new Move(new Position(6,2), new Position(7, 1))));
        StateOfGame.variant.changeState(player.getAIMove());
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "r.......\n" +
                        ".....R.W\n" +
                        ".......r\n" +
                        "......w.\n"));
        StateOfGame.variant.changeState(player.getAIMove());
        System.out.println(StateOfGame.chessboard);
        assertTrue(StateOfGame.chessboard.toString().equals(
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "r.......\n" +
                        ".....RW.\n" +
                        ".......r\n" +
                        "......w.\n"));
    }
}