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
        chessboardView = new ChessboardView(StateOfGame.chessboard.getXWidth(), StateOfGame.chessboard.getYWidth(), type, 1);
        this.removeAll();
        this.add(chessboardView);
    }


    public void updateChessboardView(){
//        chessboardView = new ChessboardView(new GridLayout(10,10));
//        System.out.println("DUPA");
//        chessboardView.setColor(1);
//        chessboardView.setIcons(0);
//        chessboardView.initChessboardFrame();
//        this.removeAll();
//        this.add(chessboardView);
//        //this.revalidate();
    }
}
