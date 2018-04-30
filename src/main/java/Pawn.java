import java.util.ArrayList;

public class Pawn extends ChessPiece {

    {label = 'P';}

    Pawn(Color a, Position b){
        super(a,b);
    }

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList <Move> resultList = new ArrayList<Move>();

        int j = 0;
        switch (this.getColor()) {
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
        if(v.to.y == 0 || v.to.y == 7){
            ArrayList <ChessPiece> temp = new ArrayList<ChessPiece>();
            temp.add(new Rook(getColor(),v.to));
            temp.add(new Knight(getColor(),v.to));
            temp.add(new Queen(getColor(),v.to));
            temp.add(new Bishop(getColor(),v.to));
            for(ChessPiece chessPiece : temp){
                Move u = new Move(v.from, v.to, true, chessPiece);
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
