
/*
Class that implements rules of Classic chess.

 */
public class ClassicChess implements VariantOfGame {

    public boolean validateMove(Move move) {
        if(move.to.x < 0 || move.to.x >= 8)return false;
        if(move.to.y < 0 || move.to.y >= 8)return false;
        return true;
    }

    public void initializeStateOfGame() {

    }

}
