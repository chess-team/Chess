
/*
Class having additional functions.
 */

public class ChessUtil {

    // simple signum function for integers
    private static int signum(int a){
        if(a < 0)return -1;
        if(a == 0)return 0;
        return 1;
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

    // check if King is under attack after move
    public static boolean isKingUnderAttackAfterMove(Move v){
        return false;
    }
}
