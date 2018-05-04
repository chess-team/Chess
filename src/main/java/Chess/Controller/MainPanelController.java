package Chess.Controller;

import Chess.Model.Chessboard;
import Chess.View.MainFrameView;

public class MainPanelController {

    private MainFrameView mainFrameView;
    private ChessboardController chessboardController;


    public MainPanelController(MainFrameView mainFrameView){
        this.mainFrameView = mainFrameView;
        this.chessboardController = new ChessboardController(mainFrameView);
    }
}
