package Chess.Model;

import Chess.Model.ChessPieces.Queen;
import Chess.Model.Moves.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
Class that represent simple text interface in console.
 */

class textUI {
    public static void main(String... args) {
        System.out.println("To make a move type coordinates of " +
                "moved chess pieces and target square. (numbers from 0 to 7)");

        boolean running = true;
        while (running) {
            Scanner in = new Scanner(System.in);
            System.out.println(StateOfGame.chessboard);
            try {
                ArrayList<Integer> inputInts = new ArrayList<>();
                for (int i = 0; i < 4; ++i) {
                    inputInts.add(in.nextInt());
                    if (inputInts.get(inputInts.size() - 1) == -2) {
                        running = false;
                        break;
                    }
                }
                // -3 - kill, -4 - set, -5 change color, -6 block  --- move
                int x = inputInts.get(2);
                int y = inputInts.get(3);
                Position to = new Position(x,y);
                Move v;
                switch (inputInts.get(0)){
                    case -3:
                        v = new KillFigureMove(to);
                        break;
                    case -4:
                        v = new SetFigureMove(to, new Queen(ChessColour.WHITE, to));
                        break;
                    case -5:
                        v = new ChangeColorOfFigureMove(to);
                        break;
                    case -6:
                        v = new BlockFigureMove(to);
                        break;
                    default:
                        v = new Move(new Position(inputInts.get(0),
                                inputInts.get(1)),
                                to);
                }

                if (StateOfGame.variant.validateMove(v)) {
                    StateOfGame.variant.changeState(v);
                    System.out.println(StateOfGame.chessboard);
                    System.out.println(StateOfGame.stateOfGameplay);
                } else {
                    System.out.println("invalid move\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
