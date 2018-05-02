package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;

import java.util.ArrayList;

/*
Class holds information about chess pieces.
 */

public abstract class ChessPiece {

    private final ChessColour chessColour;

    private Position positionOnChessboard;

    public char label;

    abstract public ArrayList<Move> listOfPossibleMoves();

    public Position getPosition(){

        return positionOnChessboard;
    }

    public ChessColour getChessColour(){

        return this.chessColour;
    }

    public void setPosition(Position a){

        this.positionOnChessboard = a;
    }

    // method used in printing chessBoard in text UI.
    @Override
    public String toString() {
        String ret = Character.toString(label);
        if(this.chessColour == ChessColour.BLACK)
            ret = Character.toString(Character.toLowerCase(label));
        return ret;
    }

    public ChessPiece(ChessColour a, Position b){
        this.chessColour = a;
        this.positionOnChessboard = b;
    }
}
