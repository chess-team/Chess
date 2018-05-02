package Chess.Model;
/*
Chess.Model.Chessboard class that represent classic chessboard.
 */

import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.Moves.Move;

public class ClassicChessboard implements Chessboard {

    public int getXWidth() {
        return 8;
    }

    public int getYWidth(){
        return 8;
    }

    // Uses standard x,y coordinates with coordinate system in left, bottom
    // corner.
    private final ChessPiece[][] board = new ChessPiece[8][8];
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

    //function used in comparing two game states.
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ClassicChessboard) && this.toString().equals(obj.toString());
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
