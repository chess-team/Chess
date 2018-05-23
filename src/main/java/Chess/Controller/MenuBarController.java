package Chess.Controller;

import Chess.Model.GameVariants.ListOfGameVariants;
import Chess.Model.StateOfGame;
import Chess.View.MainFrameView;
import Chess.View.MainPanelView;
import Chess.View.MenuBarView;

import java.awt.event.ActionListener;

class MenuBarController {

    private MainFrameView mainFrameView;
    private MainPanelView mainPanelView;
    private MenuBarView menuBarView;
    private ChessboardController chessboardController;
    private ListOfGameVariants variants = new ListOfGameVariants();

    MenuBarController(MainFrameView mainFrameView, MainPanelController mainPanelController) {
        this.mainFrameView = mainFrameView;
        this.menuBarView = mainFrameView.getMenuBarView();
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardController = mainPanelController.getChessboardController();
        addListeners();
    }

    private void addListeners() {
        addOptionsListeners();
        addChoosePlayerListener();
        addVariantOfGameListener();
        addColorListener();
    }

    private void addOptionsListeners() {
        ActionListener newGameListener = actionEvent -> {
            System.out.println("New Game");
            StateOfGame.variant.initializeStateOfGame();
            chessboardController.setFromToNull();
            mainFrameView.updateView();
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
        };
        menuBarView.addVariantOfGameListener(variantOfGameListener);
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

}