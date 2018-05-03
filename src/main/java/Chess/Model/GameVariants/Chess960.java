package Chess.Model.GameVariants;

import Chess.Model.ChessColour;
import Chess.Model.ClassicChessboard;
import Chess.Model.Moves.Castling;
import Chess.Model.StateOfGame;

import java.util.ArrayList;
import java.util.Collections;

/*
Version of chess - the placement of the pieces on
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
        setLineOfPawns(1, ChessColour.WHITE);
        setLineOfPawns(6, ChessColour.BLACK);
        setLineOfFigures(0, ChessColour.WHITE,temp);
        setLineOfFigures(7, ChessColour.BLACK,temp);
    }
}
