
/*
Class having additional functions.
 */

import java.util.ArrayList;

public class ChessUtil {

    // simple signum function for integers
    private static int signum(int a){
        if(a < 0)return -1;
        if(a == 0)return 0;
        return 1;
    }

    public static Color getOtherColor(Color oneColor){
        switch (oneColor){
            case BLACK:
                return Color.WHITE;
            case WHITE:
                return Color.BLACK;
        }
        return Color.BLACK;
    }

    // check for moves of Rook, Bishop, Queen if
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
    public static ArrayList<Move> listOfAllMoves(Color playerColor) {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i){
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); ++j){
                ChessPiece figure = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j));
                if(figure.getColor() == playerColor){
                    resultList.addAll(figure.listOfPossibleMoves());
                }
            }
        }
        return resultList;
    }


    public static Position getKingPosition(Color kingColor) {
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i){
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); ++j){
                ChessPiece figure = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j));
                if(figure.getColor() == kingColor && figure instanceof King){
                    return figure.getPosition();
                }
            }
        }
        return null;
    }

    public static int getNumberOfPiecesGivenColor(Color colorOfPieces){
        int count = 0;
        for(int i = 0; i < StateOfGame.chessboard.getXWidth(); ++i){
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); ++j){
                ChessPiece figure = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j));
                if(figure.getColor() == colorOfPieces &&
                        !(figure instanceof EmptySquare)){
                    ++count;
                }
            }
        }
        return count;
    }
}
