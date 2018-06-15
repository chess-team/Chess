package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ClassicChessboard;
import Chess.Model.StateOfGame;

import java.util.ArrayList;
import java.util.Collections;

public class TranscendentalChess extends ClassicChess {
    @Override
    public void initializeStateOfGame() {
        setClassicState();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= 8; ++i) {
            temp.add(i);
        }
        Collections.shuffle(temp);
        StateOfGame.historyOfMoves.white = new ArrayList<>();//temp;
        StateOfGame.historyOfMoves.white.addAll(temp);
        StateOfGame.chessboard = new ClassicChessboard();
        setLineOfPawns(1, ChessColour.WHITE);
        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(0, ChessColour.WHITE, temp);
        Collections.shuffle(temp);
        StateOfGame.historyOfMoves.black = new ArrayList<>();
        StateOfGame.historyOfMoves.black.addAll(temp);
        setLineOfFigures(7, ChessColour.BLACK, temp);
    }
}
