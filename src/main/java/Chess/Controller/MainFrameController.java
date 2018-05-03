package Chess.Controller;

import Chess.View.MainFrameView;

public class MainFrameController {

    private MainFrameView mainFrameView;
    private MenuBarController menuBarController;

    public MainFrameController(MainFrameView mainFrameView ){
        this.mainFrameView = mainFrameView;
        menuBarController = new MenuBarController(mainFrameView);
    }
}
