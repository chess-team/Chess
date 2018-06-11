package Chess.Controller;

import Chess.Model.GameVariants.ListOfGameVariants;
import Chess.Model.StateOfGame;
import Chess.View.*;

import javax.swing.*;
import java.awt.event.ActionListener;

class MenuBarController {

    private MainFrameView mainFrameView;
    private MainPanelView mainPanelView;
    private MenuBarView menuBarView;
    private ChessboardController chessboardController;
    private ListOfGameVariants variants = new ListOfGameVariants();
    private String gameVariant;

    MenuBarController(MainFrameView mainFrameView, MainPanelController mainPanelController) {
        this.mainFrameView = mainFrameView;
        this.menuBarView = mainFrameView.getMenuBarView();
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardController = mainPanelController.getChessboardController();
        gameVariant = variants.getNamesOfVariants().get(0);
        addListeners();
    }

    private void addListeners() {
        addOptionsListeners();
        addChoosePlayerListener();
        addVariantOfGameListener();
        addColorListener();
        addCheatListener();
        addHelpListener();
    }

    private void addOptionsListeners() {
        ActionListener newGameListener = actionEvent -> {
            System.out.println("New Game");
            StateOfGame.variant.initializeStateOfGame();
            chessboardController.setFromToNull();
            mainFrameView.updateView();
            chessboardController.makeComputerMoves();
        };
        menuBarView.addNewGameListener(newGameListener);

        ActionListener undoLastMoveListener = actionEvent -> {
            System.out.println("UNDO MOVE");
            chessboardController.undoMove();
        };
        menuBarView.addUndoLastMoveListener(undoLastMoveListener);

        ActionListener switchRotationListener = actionEvent -> {
            System.out.println("SWITCH ROTATION");
            chessboardController.switchRotation();
        };
        menuBarView.addSwitchRotationListener(switchRotationListener);
    }

    private void addChoosePlayerListener() {
        ActionListener choosePlayerListener = actionEvent -> {
            System.out.println("Mode : " + actionEvent.getActionCommand());
            chessboardController.takePieceUndo();
            chessboardController.setModeOfGame(actionEvent.getActionCommand());
        };

        menuBarView.addChoosePlayerListener(choosePlayerListener);
    }

    private void addVariantOfGameListener() {
        ActionListener variantOfGameListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand());
            StateOfGame.variant = variants.getInstance(actionEvent.getActionCommand());
            StateOfGame.variant.initializeStateOfGame();
            chessboardController.setFromToNull();
            mainFrameView.updateView();
            chessboardController.makeComputerMoves();
            gameVariant = actionEvent.getActionCommand();
        };
        menuBarView.addVariantOfGameListener(variantOfGameListener);
    }

    private void addCheatListener() {
        ActionListener cheatListener = actionEvent -> {
            System.out.println("Cheat : " + actionEvent.getActionCommand());
            chessboardController.setSelectedCheat(actionEvent.getActionCommand());
            switch (actionEvent.getActionCommand()) {
                case "pf":
                    break;
                case "kf":
                    JOptionPane.showMessageDialog(mainFrameView, "Choose figure to kill");
                    break;
                case "cc":
                    JOptionPane.showMessageDialog(mainFrameView, "Choose figure to change color");
                    break;
                case "sf":
                    JOptionPane.showMessageDialog(mainFrameView, "Choose square to place new figure");
                    break;
                case "bf":
                    JOptionPane.showMessageDialog(mainFrameView, "Choose figure to block");
                    break;

            }
        };

        menuBarView.addCheatListener(cheatListener);
    }

    private void addColorListener() {
        ActionListener colorMenuListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand() + " color");
            int colorType = Integer.valueOf(actionEvent.getActionCommand());
            mainPanelView.changeChessboardColour(colorType);
            mainFrameView.updateView();

        };
        menuBarView.addColorListener(colorMenuListener);
    }

    private void addHelpListener(){
        ActionListener helpListener = actionEvent -> {
            switch (actionEvent.getActionCommand() ){
                case "t":
                    TipOfTheDayView.showDialog(mainFrameView,"Tip");
                    break;
                case "v":
                    HelpView.showDialog(mainFrameView,"Game Variant Help",gameVariant);
                    break;
                case "c":
                    HelpView.showDialog(mainFrameView,"Cheats","c");
                    break;
            }
        };
        menuBarView.addHelpListener(helpListener);
    }

}