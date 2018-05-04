package Chess.Controller;

import Chess.View.MainFrameView;

public class MainFrameController {

    private MainFrameView mainFrameView;
    private MenuBarController menuBarController;
    private MainPanelController mainPanelController;

    public MainFrameController(MainFrameView mainFrameView ){
        this.mainFrameView = mainFrameView;
        mainPanelController = new MainPanelController(mainFrameView);
        menuBarController = new MenuBarController(mainFrameView, mainPanelController);

    }
}
