package Chess.Model;

import Chess.Model.Moves.Move;

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

                Move v = new Move(new Position(inputInts.get(0),
                        inputInts.get(1)),
                        new Position(inputInts.get(2), inputInts.get(3)));
                if (StateOfGame.variant.validateMove(v)) {
                    StateOfGame.variant.changeState(v);
                    System.out.println(StateOfGame.chessboard);
                } else {
                    System.out.println("invalid move\n");
                }

            } catch (Exception ignored) {
            }
        }
    }


}
