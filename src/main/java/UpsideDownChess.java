
/*
Chess that have different starting position that normal chess. White
Chess pieces are swapped with black ones. (So pawns are one step from
promotion).
 */
public class UpsideDownChess extends ClassicChess{

    @Override
    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(6,Color.WHITE);
        setLineOfPawns(1,Color.BLACK);
        setLineOfFigures(7, Color.WHITE);
        setLineOfFigures(0, Color.BLACK);
    }
}
