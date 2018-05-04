package Chess.Controller;

import Chess.Model.GameVariants.Chess960;
import Chess.Model.GameVariants.ClassicChess;
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

    MenuBarController( MainFrameView mainFrameView, MainPanelController mainPanelController ){
        this.mainFrameView = mainFrameView;
        this.menuBarView = mainFrameView.getMenuBarView();
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardController = mainPanelController.getChessboardController();
        addListeners();
    }


    void addListeners(){
        ActionListener colorMenuListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand() + " color");
            int colorType = Integer.valueOf(actionEvent.getActionCommand());
            mainPanelView.changeChessboardColour(colorType);
            mainFrameView.updateView();

        };
        menuBarView.addColorListener(colorMenuListener);

        ActionListener newGameListener = actionEvent -> {
            System.out.println("New Game");
            StateOfGame.variant.initializeStateOfGame();
            mainFrameView.updateView();
        };

        menuBarView.addNewGameListener(newGameListener);

        ActionListener undoLastMoveListener = actionEvent -> {
            System.out.println("IMPLEMENT ME");
        };
        menuBarView.addUndoLastMoveListener(undoLastMoveListener);

        ActionListener variantOfGameListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand());
            switch (actionEvent.getActionCommand() ){
                case "Classic Chess":
                    StateOfGame.variant = new ClassicChess();
                        break;
                case "Chess 960":
                    StateOfGame.variant = new Chess960();
                    break;
            }
            StateOfGame.variant.initializeStateOfGame();
            mainFrameView.updateView();
        };
        menuBarView.addVariantOfGameListener(variantOfGameListener);

        ActionListener promoteListener = actionEvent -> {
            System.out.println("Promote to " + actionEvent.getActionCommand());
            chessboardController.takePieceUndo();
            chessboardController.setPromote(actionEvent.getActionCommand());

        };
        menuBarView.addPromoteListener(promoteListener);

    }



}
