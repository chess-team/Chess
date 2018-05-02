package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
    {label = 'B';}

    public Bishop(ChessColour a, Position b){
        super(a,b);
    }


    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();
        for(int i = 1; i < 8; ++i) {
            for(int j = -1; j <= 1; j += 2) {
                for(int k = -1; k <= 1; k += 2) {
                    Move v = new Move(getPosition(),
                            getPosition().translateByVector(i*j, i*k));
                    if (StateOfGame.variant.validateMove(v)) {
                        resultList.add(v);
                    }
                }
            }
        }

        return resultList;
    }
}