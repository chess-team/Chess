public class DunsanysChess extends KillKingChess{

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(0,Color.WHITE);
        setLineOfPawns(1,Color.WHITE);
        setLineOfPawns(2,Color.WHITE);
        setLineOfPawns(3,Color.WHITE);

        setLineOfPawns(6,Color.BLACK);
        setLineOfFigures(7, Color.BLACK);
    }
}
