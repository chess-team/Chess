package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    {label = 'P';}

    public Pawn(ChessColour a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<>();

        int j = 0;
        switch (this.getChessColour()) {
            case BLACK:
                j = -1;
                break;
            case WHITE:
                j = 1;
                break;
        }
        for(int i = -1; i <= 1; ++i) {
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i, j));

            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }
        Move v = new Move(getPosition(),
                getPosition().translateByVector(0, 2*j));
        if(v.to.y == 0 || v.to.y == StateOfGame.chessboard.getYWidth() - 1){
            ArrayList <ChessPiece> temp = new ArrayList<>();
            temp.add(new Rook(getChessColour(),v.to));
            temp.add(new Knight(getChessColour(),v.to));
            temp.add(new Queen(getChessColour(),v.to));
            temp.add(new Bishop(getChessColour(),v.to));
            for(ChessPiece chessPiece : temp){
                Move u = new Move(v.from, v.to, chessPiece);
                if (StateOfGame.variant.validateMove(u)) {
                    resultList.add(u);
                }
            }
        }
        else {
            if (StateOfGame.variant.validateMove(v)) {
                resultList.add(v);
            }
        }

        return resultList;
    }
}
