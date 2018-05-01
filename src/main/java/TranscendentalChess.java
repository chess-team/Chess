import java.util.ArrayList;
import java.util.Collections;

public class TranscendentalChess extends ClassicChess {
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
        Collections.shuffle(temp);
        setLineOfFigures(7, Color.BLACK,temp);
    }
}
