
/*
Chessboard class that represent classic chessboard.
 */

public class ClassicChessboard implements Chessboard {

    // Uses standard x,y coordinates with coordinate system in left, bottom
    // corner.
    ChessPiece[][] board = new ChessPiece[8][8];
    {
        for(int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                board[i][j] = new EmptySquare();
            }
        }
    }
    public Position getPositionOfChessPiece(ChessPiece figure) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ChessPiece getChessPieceOnPosition(Position a) {
        return board[a.x][a.y];
    }

    public void moveFigure(Move v){
        board[v.to.x][v.to.y] = board[v.from.x][v.from.y];
        board[v.from.x][v.from.y] = new EmptySquare();
    }
}
