package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.Pawn;

// modified version of Dunsany's Chess
public class DunsanysChess extends ClassicChess{

    public void initializeStateOfGame() {
        super.initializeStateOfGame();
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(0, ChessColour.WHITE);
        setLineOfPawns(1, ChessColour.WHITE);
        setLineOfPawns(2, ChessColour.WHITE);
        setLineOfPawns(3, ChessColour.WHITE);

        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(7, ChessColour.BLACK);
        StateOfGame.stateOfGameplay = StateOfGameplay.BLACK_MOVE;
    }

    @Override
    protected void inCaseOfEndOfGame(){
        ChessColour colorOfPossibleLoser = ChessColour.WHITE;
        if(StateOfGame.stateOfGameplay == StateOfGameplay.BLACK_MOVE){
            colorOfPossibleLoser = ChessColour.BLACK;
        }
        if(StateOfGame.chessboard.
                getPositionOfChessPiece(Pawn.class, colorOfPossibleLoser) == null){
            StateOfGame.stateOfGameplay =
                    StateOfGameplay.BLACK_WON;
            return;
        }

        if(ChessUtil.listOfAllMoves(colorOfPossibleLoser).isEmpty()) {
            if (isKingUnderAttack(colorOfPossibleLoser)) {
                switch (colorOfPossibleLoser){
                    case WHITE:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.BLACK_WON;
                        break;
                    case BLACK:
                        StateOfGame.stateOfGameplay =
                                StateOfGameplay.WHITE_WON;
                        break;
                }
            }
            else {
                StateOfGame.stateOfGameplay = StateOfGameplay.DRAW;
            }
        }

        drawRule3TimesSamePosition();
    }
}
