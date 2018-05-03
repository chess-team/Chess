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
        this.setTitle("Chess.Model.Chessboard");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setVisible(true);
    }



    public void updateView(){
        //mainPanelView.updateChessboardView();
        this.revalidate();
    }



    public MainPanelView getMainPanelView() {
        return mainPanelView;
    }

    public MenuBarView getMenuBarView() {
        return menuBarView;
    }


}
