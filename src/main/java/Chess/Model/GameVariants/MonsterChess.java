package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.King;
import Chess.Model.ChessPieces.Pawn;
import Chess.Model.Moves.Move;

/* Black player have normal figures and one move per turn.
White player, have king and four pawns, but 2 moves per turn.
    */
public class MonsterChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;

    public void initializeStateOfGame() {
        numberOfConsecutiveSameColorMoves = 0;
        super.initializeStateOfGame();
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
        StateOfGame.historyOfMoves.clear();
        StateOfGame.chessboard = new ClassicChessboard();
        StateOfGame.chessboard.setFigure(
                new King(ChessColour.WHITE, new Position(
                        4,0)));
        for(int i = 2; i < 6; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(ChessColour.WHITE, new Position(i,1)));
        }
        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(7, ChessColour.BLACK);
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
    }

    @Override
    protected void changeStateOfGameplay() {
        ++numberOfConsecutiveSameColorMoves;
        int numberOfAllowedPlayerMoves = 2;
        if(StateOfGame.stateOfGameplay == StateOfGameplay.BLACK_MOVE){
            numberOfAllowedPlayerMoves = 1;
        }
        if(numberOfConsecutiveSameColorMoves == numberOfAllowedPlayerMoves) {
            numberOfConsecutiveSameColorMoves = 0;
            swapColor();
            swapPlayerColor();
        }
        inCaseOfEndOfGame();
    }
}
