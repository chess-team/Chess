package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    {label = 'K';}

    public Knight(ChessColour a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<>();
        for(int i = -2; i <= 2; i += 4) {
            for(int j = -1; j <= 1; j += 2) {
                Move v = new Move(getPosition(),
                        getPosition().translateByVector(i,j));

                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }

                v = new Move(getPosition(),
                        getPosition().translateByVector(j,i));

                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }
            }
        }

        return resultList;
    }
}
