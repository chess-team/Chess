
/*
Chessboard class that represent classic chessboard.
 */

public class ClassicChessboard implements Chessboard {

    // Uses standard x,y coordinates with coordinate system in left, bottom
    // corner.
    private ChessPiece[][] board = new ChessPiece[8][8];
    {
        for(int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                board[i][j] = new EmptySquare(new Position(i,j));
            }
        }
    }

    public Position getPositionOfChessPiece(ChessPiece figure) {
        for(int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                if(board[i][j].equals(figure))
                    return new Position(i,j);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(int y = 7; y >= 0; --y){
            for(int x = 0; x <= 7; ++x){
                ret.append(board[x][y].toString());
            }
            ret.append('\n');
        }
        return ret.toString();
    }

    public ChessPiece getChessPieceOnPosition(Position a) {
        return board[a.x][a.y];
    }

    public void moveFigure(Move v){
        board[v.to.x][v.to.y] = board[v.from.x][v.from.y];
        board[v.from.x][v.from.y] = new EmptySquare(new Position(v.from.x, v.from.y));
        board[v.to.x][v.to.y].setPosition(new Position(v.to.x, v.to.y));
    }

    public void setFigure(ChessPiece figure) {
        Position place = figure.getPosition();
        board[place.x][place.y] = figure;
    }
}
