import java.util.ArrayList;
import java.util.Scanner;

/*
Class that represent simple text interface in console.
 */
@SuppressWarnings("SpellCheckingInspection")
class textUI {
    public static void main(String... args){
        System.out.println("Aby wykonać ruch należy podać współrzędne" +
                " dwóch pol najpierw pola z którego ruszmy figurę, potem" +
                " gdzie ruszmy");
        //noinspection InfiniteLoopStatement
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println(StateOfGame.chessboard);
            try {
                ArrayList <Integer> inputInts = new ArrayList<Integer>();
                for(int i = 0; i < 4; ++i) {
                    inputInts.add(in.nextInt());
                }
                Move v = new Move(new Position(inputInts.get(0),
                        inputInts.get(1)),
                        new Position(inputInts.get(2),inputInts.get(3)));
                if(StateOfGame.variant.validateMove(v)){
                    StateOfGame.variant.changeState(v);
                    System.out.println(StateOfGame.chessboard);
                }
                else {
                    System.out.println("invalid move\n");
                }

            } catch (Exception ignored){}
        }
    }


}
