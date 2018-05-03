package Chess.Controller;

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

        ActionListener colorMenuListener = actionEvent -> {
            System.out.println(actionEvent.getActionCommand() + " color");
            int colorType = Integer.valueOf(actionEvent.getActionCommand());
            mainPanelView.changeChessboarColour(colorType);
            mainFrameView.updateView();

        };
        menuBarView.addColorListeners(colorMenuListener);

        ActionListener variantOfGameMenuListener = actionEvent -> {
            //TODO
        };
        ActionListener optionsMenuListener = actionEvent -> {
            //TODO
        };

    }



}
