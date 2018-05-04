package Chess.View;

import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {



    private ChessboardView chessboardView;

    MainPanelView(){
        super();
        chessboardView = new ChessboardView(StateOfGame.chessboard.getXWidth(), StateOfGame.chessboard.getYWidth(), 0, 1);
        this.add(chessboardView);
    }
    public void changeChessboarColour(int type){
        chessboardView.setColor(type);
    }

    public void updateMainPanelView(){
        chessboardView.updateChessboard();
    }

    public ChessboardView getChessboardView() {
        return chessboardView;
    }
}
