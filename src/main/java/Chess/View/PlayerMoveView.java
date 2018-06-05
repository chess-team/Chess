package Chess.View;

import Chess.Model.StateOfGame;
import Chess.Model.StateOfGameplay;

import javax.swing.*;
import java.awt.*;

public class PlayerMoveView extends JPanel {
    private JLabel message;
    PlayerMoveView() {
        super();
        message = new JLabel();
        message.setText("WHITE PLAYER MOVE");
        message.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(message);
    }
    public void updatePlayerMoveView() {
        if(StateOfGame.getStateOfGameplay()== StateOfGameplay.WHITE_MOVE){
            message.setText("WHITE PLAYER MOVE");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.BLACK_MOVE){
            message.setText("BLACK PLAYER MOVE");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.BLACK_WON){
            message.setText("BLACK PLAYER WON");
        }else if(StateOfGame.getStateOfGameplay()==StateOfGameplay.DRAW){
            message.setText("DRAW");
        }else{
            message.setText("WHITE PLAYER WON");
        }
    }

    public void scale(int type) {
        if (type == 0) {
            message.setFont(new Font("Dialog", Font.BOLD, 20));
        } else if (type == 1) {
            message.setFont(new Font("Dialog", Font.BOLD, 16));
        } else {
            message.setFont(new Font("Dialog", Font.BOLD, 10));
        }
    }
}
