package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.King;
import Chess.Model.ChessPieces.Pawn;
import Chess.Model.Moves.Move;

public class MonsterChess extends KillKingChess {

    private int numberOfConsecutiveSameColorMoves = 0;

    public void initializeStateOfGame() {
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
    }

    @Override
    public void changeState(Move change) {

        switch(colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
                swapColor();
                numberOfConsecutiveSameColorMoves = 0;
                break;
            case WHITE:
                if(numberOfConsecutiveSameColorMoves == 1){
                    StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_MOVE;
                    swapColor();
                    numberOfConsecutiveSameColorMoves = 0;
                }
                ++numberOfConsecutiveSameColorMoves;
                break;
        }
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);
        inCaseOfEndOfGame();
    }
}
