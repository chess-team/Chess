import java.util.ArrayList;
import java.util.Collections;

/*
Version of chess without Castling, additionally the placement of the pieces on
the first rank is randomised, with the opponent's pieces mirroring it.
 */
public class Chess960 extends ClassicChess{

    @Override
    public void initializeStateOfGame() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 1; i <= 8; ++i){
            temp.add(i);
        }
        Collections.shuffle(temp);
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(1,Color.WHITE);
        setLineOfPawns(6,Color.BLACK);
        setLineOfFigures(0, Color.WHITE,temp);
        setLineOfFigures(7, Color.BLACK,temp);
    }
}
