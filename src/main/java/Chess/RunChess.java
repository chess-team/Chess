package Chess;

import Chess.Controller.MainFrameController;
import Chess.View.MainFrameView;

import javax.swing.*;

public class RunChess {
    public static void main(String[] args) {
        MainFrameView mainFrame = new MainFrameView();
        new MainFrameController(mainFrame);
        mainFrame.setVisible(true);
    }
}
