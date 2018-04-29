
/*
Class holds information about position of all chess pieces.
 */

public interface Chessboard {
    Position getPositionOfChessPiece(ChessPiece figure);
    ChessPiece getChessPieceOnPosition(Position a);
    public void moveFigure(Move v);
    public void setFigure(Position place, ChessPiece figure);
}
