
/*
Singleton class that have information about state of game, variant of game and
history of moves.
*/

public class StateOfGame {

    private static StateOfGame realStateOfGame = new StateOfGame();

    private StateOfGame(){}

    // return only instance of singleton class
    public static StateOfGame getInstance(){
        return realStateOfGame;
    }

    // method changes state after player move.
    void changeState(Move change){
        ;
    }

    Chessboard chessboard;
    public static VariantOfGame variant;

    // Method that parse string in format of toString() method and initialize
    // fields of class. This method is used to load saved game from hard drive.
    void loadStateOfGame(String savedState){
        ;
    }

    // Method that save all information about state of game to string.
    // This method is used to save state of game to hard drive.
    @Override
    public String toString() {
        return super.toString();
    }
}
