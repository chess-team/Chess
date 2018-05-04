package Chess.Controller;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
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
        addButtonListener();

    }


    private void addButtonListener(){

        ActionListener buttonListener = actionEvent -> {
            String [] s = actionEvent.getActionCommand().split(" ",2);
            Position position = new Position(Integer.valueOf(s[0]),Integer.valueOf(s[1]));

            if( from == null ){
                ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
                if( figure.getClass() != EmptySquare.class && isColorOfMovedPieceCorrect(figure.getChessColour()) ){
                    //System.out.println("TAKING PIECE");
                    takePiece(position);

                }//else System.out.println("INVALID PIECE");
            }else {
                Move move = new Move(from,position);
                if( StateOfGame.variant.validateMove(move) ){
                    //System.out.println("Moving piece");
                    movePiece(move);
                    checkState();

                }else{
                    //System.out.println("INVALID MOVE");
                    takePieceUndo();
                }

            }
        };
        chessboardView.setActionListener(buttonListener);

    }
    private boolean isColorOfMovedPieceCorrect (ChessColour colorOfMovedPiece){
        switch (StateOfGame.getStateOfGameplay()){
            case WHITE_MOVE:
                return colorOfMovedPiece == ChessColour.WHITE;
            case BLACK_MOVE:
                return colorOfMovedPiece == ChessColour.BLACK;
        }
        return false;
    }

    private void restartGame(){
        StateOfGame.variant.initializeStateOfGame();
        mainFrameView.updateView();
    }
    private void checkState(){
        System.out.println(StateOfGame.getStateOfGameplay());
        switch (StateOfGame.getStateOfGameplay() ){
            case DRAW:
            case WHITE_WON:
            case BLACK_WON:
                restartGame();
                break;
        }
    }

    private void takePiece(Position position){
        chessboardView.highlightPosition(position);
        chessboardView.highlightPossiblePositions(position);
        from = position;
    }
    private void movePiece( Move move ){
        chessboardView.highlightPositionUndo(from);
        chessboardView.highlightPossiblePositionsUndo(from);
        StateOfGame.variant.changeState(move);
        mainFrameView.updateView();
        from = null;
    }
    private void takePieceUndo(){
        chessboardView.highlightPositionUndo(from);
        chessboardView.highlightPossiblePositionsUndo(from);
        from = null;
    }
}
