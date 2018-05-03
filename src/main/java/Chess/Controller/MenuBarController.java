package Chess.Controller;

import Chess.Model.StateOfGame;
import Chess.View.MainFrameView;
import Chess.View.MainPanelView;
import Chess.View.MenuBarView;

import java.awt.event.ActionListener;

class MenuBarController {

    private MainFrameView mainFrameView;
    private MainPanelView mainPanelView;
    private MenuBarView menuBarView;


    MenuBarController( MainFrameView mainFrameView ){
        this.mainFrameView = mainFrameView;
        this.menuBarView = mainFrameView.getMenuBarView();
        this.mainPanelView = mainFrameView.getMainPanelView();
        addListeners();
    }
    void addListeners(){
        ActionListener colorMenuListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand() + " color");
            int colorType = Integer.valueOf(actionEvent.getActionCommand());
            mainPanelView.changeChessboarColour(colorType);
            mainFrameView.updateView();

        };
        menuBarView.addColorListener(colorMenuListener);

        ActionListener newGameListener = actionEvent -> {
            System.out.println("New Game");
            StateOfGame.variant.initializeStateOfGame();
            mainPanelView.getChessboardView().updateChessboard();
            mainFrameView.updateView();
        };

        menuBarView.addNewGameListener(newGameListener);

        ActionListener undoLastMoveListener = actionEvent -> {
            System.out.println("IMPLEMENT ME");
        };
        menuBarView.addUndoLastMoveListener(undoLastMoveListener);

    }



}
