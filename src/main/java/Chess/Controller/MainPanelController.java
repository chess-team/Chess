package Chess.Controller;


import Chess.View.MainFrameView;
import Chess.View.MainPanelView;
import Chess.View.TipOfTheDayView;

import javax.swing.*;
import java.awt.event.*;


class MainPanelController {

    private MainFrameView mainFrameView;

    private ChessboardController chessboardController;
    private MainPanelView mainPanelView;


    MainPanelController(MainFrameView mainFrameView) {
        this.mainFrameView = mainFrameView;
        this.chessboardController = new ChessboardController(mainFrameView);
        this.mainPanelView = mainFrameView.getMainPanelView();
        setKeyStrokes();
        addComponentResized();
        addWindowListener();
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
                mainFrameView.getMenuBarView().switchRotationButton();

            }
        });

        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK), "playFair");
        mainPanelView.getActionMap().put("playFair", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(mainFrameView, "Congratulations - you decided to play fair");
                chessboardController.setSelectedCheat("pf");
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK), "killFigure");
        mainPanelView.getActionMap().put("killFigure", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(mainFrameView, "Choose figure to kill");
                chessboardController.setSelectedCheat("kf");
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK), "changeColourOfFigure");
        mainPanelView.getActionMap().put("changeColourOfFigure", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(mainFrameView, "Choose figure to change color");
                chessboardController.setSelectedCheat("cc");
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK), "setFigure");
        mainPanelView.getActionMap().put("setFigure", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(mainFrameView, "Choose square to place new figure");
                chessboardController.setSelectedCheat("sf");
            }
        });
        mainPanelView.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_MASK), "blockFigure");
        mainPanelView.getActionMap().put("blockFigure", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(mainFrameView, "Choose figure to block");
                chessboardController.setSelectedCheat("bf");
            }
        });



    }

    private void addComponentResized() {
        mainFrameView.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                if (mainFrameView.getWidth() <= 850 || mainFrameView.getHeight() <= 350) {
                    mainPanelView.scale(2);
                } else if (mainFrameView.getWidth() <= 1000 || mainFrameView.getHeight() <= 570) {
                    mainPanelView.scale(1);
                } else {
                    mainPanelView.scale(0);
                }
            }
        });
    }

    private void addWindowListener() {
        mainFrameView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit Chess",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    mainFrameView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }

            @Override
            public void windowOpened(WindowEvent windowEvent) {
                TipOfTheDayView.showDialog(mainFrameView,"Tip Of The Day");
            }
        });

    }


    ChessboardController getChessboardController() {
        return chessboardController;
    }

}
