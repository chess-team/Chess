package Chess.View;

import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

import javax.swing.*;
import java.awt.*;

public class PlayerMoveView extends JLabel{
    PlayerMoveView() {
        super();
        setText("WHITE PLAYER MOVE");
        setFont(new Font("Dialog", Font.BOLD, 20));
    }
    public void updatePlayerMoveView() {
        if(StateOfGame.getStateOfGameplay()== StateOfGameplay.WHITE_MOVE){
            setText("WHITE PLAYER MOVE");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.BLACK_MOVE){
            setText("BLACK PLAYER MOVE");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.BLACK_WON){
            setText("BLACK PLAYER WON");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.DRAW){
            setText("DRAW");
        }else{
            setText("WHITE PLAYER WON");
        }
    }

    public void scale(int type) {
        if (type == 0) {
            setFont(new Font("Dialog", Font.BOLD, 20));
        } else if (type == 1) {
            setFont(new Font("Dialog", Font.BOLD, 16));
        } else {
            setFont(new Font("Dialog", Font.BOLD, 10));
        }
    }
}
