package Chess.View;

import Chess.Model.ChessColour;
import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {



    private ChessboardView chessboardView;
    private CapturedPiecesView capturedPiecesViewWhite,capturedPiecesViewBlack;

    MainPanelView(){
        super(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        chessboardView = new ChessboardView(StateOfGame.chessboard.getXWidth(), StateOfGame.chessboard.getYWidth(), 0, 1);
        capturedPiecesViewWhite= new CapturedPiecesView(ChessColour.WHITE);
        capturedPiecesViewWhite.setPiecesIcons(chessboardView.getWhitePiecesIcons());
        capturedPiecesViewBlack= new CapturedPiecesView(ChessColour.BLACK);
        capturedPiecesViewBlack.setPiecesIcons(chessboardView.getBlackPiecesIcons());
        this.add(capturedPiecesViewWhite,c);
        this.add(chessboardView,c);
        this.add(capturedPiecesViewBlack,c);
    }

    public CapturedPiecesView getCapturedPiecesViewWhite() {
        return capturedPiecesViewWhite;
    }

    public CapturedPiecesView getCapturedPiecesViewBlack() {
        return capturedPiecesViewBlack;
    }

    public void changeChessboardColour(int type){
        chessboardView.setColor(type);
    }

    public void updateMainPanelView(){
        chessboardView.updateChessboard();
    }

    public ChessboardView getChessboardView() {
        return chessboardView;
    }
}
