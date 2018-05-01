
/*
Class holds information about position of all chess pieces.
 */

@SuppressWarnings({"SameReturnValue", "unused"})
public interface Chessboard {
    Position getPositionOfChessPiece(ChessPiece figure);
    ChessPiece getChessPieceOnPosition(Position a);
    void moveFigure(Move v);
    void setFigure(ChessPiece figure);
    int getXWidth();
    int getYWidth();
}
