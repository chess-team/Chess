
/*
Class having additional functions.
*/
public class ChessUtil {

    // check for moves of Rook, Bishop, Queen if
    // moves would pass through some figure
    public static boolean isMovePassingThroughFigure(Move v){
        boolean changeInXCoordinate = v.from.x == v.to.x;
        boolean changeInYCoordinate = v.from.y == v.to.y;
        int difference = Math.abs(v.from.x - v.to.x) - 1;
        for(int i = 1; i <= difference; ++i){
            int j = 0, k = 0;
            if(changeInXCoordinate) j = 1;
            if(changeInYCoordinate) k = 1;
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
