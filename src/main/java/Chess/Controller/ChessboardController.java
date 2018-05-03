package Chess.Controller;

import Chess.Model.Chessboard;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;
import Chess.View.MainFrameView;
import Chess.View.MainPanelView;

import java.awt.event.ActionListener;

public class ChessboardController {

    private MainFrameView mainFrameView;
    private MainPanelView mainPanelView;
    private ChessboardView chessboardView;
    private Position from;

    public ChessboardController(MainFrameView mainFrameView){
        this.mainFrameView = mainFrameView;
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardView = this.mainPanelView.getChessboardView();
        ActionListener buttonListener = actionEvent -> {
            //System.out.println(actionEvent.getActionCommand());
            String [] s = actionEvent.getActionCommand().split(" ",2);
            Position position = new Position(Integer.valueOf(s[0]),Integer.valueOf(s[1]));
            if( from == null ){
                System.out.println("Taking piece");
                chessboardView.highlightPosition(position);
                from = position;
            }else {
                System.out.println("Moving piece");
                StateOfGame.chessboard.moveFigure(new Move(from,position));
                chessboardView.updateIcon(from);
                chessboardView.updateIcon(position);
                mainFrameView.updateView();
                chessboardView.highlightPositionUndo(from);
                from = null;
            }
        };
        chessboardView.setActionListener(buttonListener);
    }
}
