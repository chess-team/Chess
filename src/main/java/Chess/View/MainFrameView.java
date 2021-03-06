package Chess.View;

import javax.swing.*;

public class MainFrameView extends JFrame {

    private MainPanelView mainPanelView;
    private MenuBarView menuBarView;

    public MainFrameView(){
        super();
        mainPanelView = new MainPanelView();
        menuBarView = new MenuBarView();
        this.setJMenuBar(menuBarView);
        this.setContentPane(mainPanelView);
        this.setTitle("Chess");
        this.setSize(1300, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateView(){
        mainPanelView.updateMainPanelView();
        this.revalidate();
    }


    public MainPanelView getMainPanelView() {
        return mainPanelView;
    }

    public MenuBarView getMenuBarView() {
        return menuBarView;
    }


}
