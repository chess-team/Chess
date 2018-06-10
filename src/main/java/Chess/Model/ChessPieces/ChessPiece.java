package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;

import java.util.ArrayList;

/*
Class holds information about chess pieces
(about rules of chess piece movement).
 */

public abstract class ChessPiece {

    private ChessColour chessColour;

    private Position positionOnChessboard;

    public char label;

    abstract public ArrayList<Move> listOfPossibleMoves();

    public Position getPosition() {
        return positionOnChessboard;
    }

    public ChessColour getChessColour() {
        return this.chessColour;
    }

    public void setPosition(Position a) {
        this.positionOnChessboard = a;
    }

    public void setColor(ChessColour a) {
        this.chessColour = a;
    }

    // method used in printing chessBoard in text UI.
    @Override
    public String toString() {
        String ret = Character.toString(label);
        if (this.chessColour == ChessColour.BLACK)
            ret = Character.toString(Character.toLowerCase(label));
        return ret;
    }

    ChessPiece(ChessColour a, Position b) {
        this.chessColour = a;
        this.positionOnChessboard = b;
    }

    // method that check if move is breaking rules connected with this
    // chess piece (it doesn't check other rules)
    // so for most chess pieces it will only check if difference in coordinates
    // could be correct
    abstract public boolean isBreakingRules(Move move);
}
