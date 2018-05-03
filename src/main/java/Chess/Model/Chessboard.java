package Chess.Model;
/*
Class holds information about position of all chess pieces.
 */

import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.Moves.Move;

@SuppressWarnings({"SameReturnValue", "unused"})
public interface Chessboard {
    Position getPositionOfChessPiece(Class classOfFigure,
                                     ChessColour colorOfFigure);
    ChessPiece getChessPieceOnPosition(Position a);
    void moveFigure(Move v);
    void setFigure(ChessPiece figure);
    int getXWidth();
    int getYWidth();
}
