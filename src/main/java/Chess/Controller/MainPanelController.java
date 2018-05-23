package Chess.Controller;

import Chess.Model.Chessboard;
import Chess.View.MainFrameView;
import Chess.View.MainPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainPanelController {

    private MainFrameView mainFrameView;

    private ChessboardController chessboardController;
    private MainPanelView mainPanelView;


    MainPanelController(MainFrameView mainFrameView) {
        this.mainFrameView = mainFrameView;
        this.chessboardController = new ChessboardController(mainFrameView);
        this.mainPanelView = mainFrameView.getMainPanelView();
        setKeyStrokes();
    }

    private void setKeyStrokes() {
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK), "undoMove");
        mainPanelView.getActionMap().put("undoMove", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chessboardController.undoMove();
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), "newGame");
        mainPanelView.getActionMap().put("newGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chessboardController.restartGame();
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK), "switchRotation");
        mainPanelView.getActionMap().put("switchRotation", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chessboardController.switchRotation();
            }
        });
    }


    public ChessboardController getChessboardController() {
        return chessboardController;
    }

}
