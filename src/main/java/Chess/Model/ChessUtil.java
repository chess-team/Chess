package Chess.Model;
/*
Class having additional functions.
 */

import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.ChessPieces.King;
import Chess.Model.Moves.Move;

import java.util.ArrayList;

public class ChessUtil {

    // simple signum function for integers
    @SuppressWarnings("SpellCheckingInspection")
    public static int signum(int a){
        if(a < 0)return -1;
        if(a == 0)return 0;
        return 1;
    }

    public static ChessColour getOtherColor(ChessColour oneColor){
        switch (oneColor){
            case BLACK:
                return ChessColour.WHITE;
            case WHITE:
                return ChessColour.BLACK;
        }
        return ChessColour.BLACK;
    }

    // check for moves of Rook, Bishop, Queen, Pawn if
    // moves would pass through some figure
    public static boolean isMovePassingThroughFigure(Move v){
        int differenceOnXCoordinate = v.from.x - v.to.x;
        int differenceOnYCoordinate = v.from.y - v.to.y;

        int difference = Math.max(Math.abs(differenceOnXCoordinate),
                Math.abs(differenceOnYCoordinate)) - 1;
        for(int i = 1; i <= difference; ++i){
            int j = signum(v.to.x - v.from.x);
            int k = signum(v.to.y - v.from.y);
            if(!(StateOfGame.chessboard.getChessPieceOnPosition(
                    v.from.translateByVector(i*j,i*k))instanceof EmptySquare)){
                return true;
            }
        }
        return false;
    }

    // list of all moves for Player with given color.
    public static ArrayList<Move> listOfAllMoves(ChessColour playerColor) {
        ArrayList <Move> resultList = new ArrayList<>();
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i){
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); ++j){
                ChessPiece figure = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j));
                if(figure.getChessColour() == playerColor){
                    resultList.addAll(figure.listOfPossibleMoves());
                }
            }
        }
        resultList.addAll(StateOfGame.variant.
                getSpecialMoves().listOfPossibleMoves(playerColor));

        return resultList;
    }


    public static Position getKingPosition(ChessColour kingColor) {
        return StateOfGame.chessboard.
                getPositionOfChessPiece(King.class, kingColor);
    }

    public static int getNumberOfPiecesGivenColor(ChessColour colorOfPieces){
        int count = 0;
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i){
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); ++j){
                ChessPiece figure = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j));
                if(figure.getChessColour() == colorOfPieces &&
                        !(figure instanceof EmptySquare)){
                    ++count;
                }
            }
        }
        return count;
    }
}
