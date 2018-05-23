package Chess.View;

import Chess.Model.ChessColour;
import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainPanelView extends JPanel {



    private ChessboardView chessboardView;
    private CapturedPiecesView capturedPiecesViewWhite,capturedPiecesViewBlack;
    private PlayerMoveView playerMoveView;

    MainPanelView(){
        super(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        chessboardView = new ChessboardView();
        capturedPiecesViewWhite= new CapturedPiecesView(ChessColour.WHITE);
        capturedPiecesViewWhite.setPiecesIcons(chessboardView.getWhitePiecesIcons());
        capturedPiecesViewBlack= new CapturedPiecesView(ChessColour.BLACK);
        capturedPiecesViewBlack.setPiecesIcons(chessboardView.getBlackPiecesIcons());
        playerMoveView= new PlayerMoveView();
        this.add(capturedPiecesViewWhite,c);
        this.add(chessboardView,c);
        this.add(capturedPiecesViewBlack,c);
        c.gridx=4;
        c.anchor=GridBagConstraints.PAGE_END;
        this.add(playerMoveView,c);

    }


    public void changeChessboardColour(int type){
        chessboardView.setColor(type);
    }

    public void updateMainPanelView(){
        chessboardView.updateChessboard();
        playerMoveView.updatePlayerMoveView();
        capturedPiecesViewBlack.updateCapturedPiecesView();
        capturedPiecesViewWhite.updateCapturedPiecesView();
    }

    public ChessboardView getChessboardView() {
        return chessboardView;
    }
}
