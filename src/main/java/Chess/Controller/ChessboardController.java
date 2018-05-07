package Chess.Controller;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.*;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;
import Chess.View.MainFrameView;
import Chess.View.MainPanelView;

import javax.swing.*;
import java.awt.event.ActionListener;

class ChessboardController {

    private final MainFrameView mainFrameView;
    private final MainPanelView mainPanelView;
    private final ChessboardView chessboardView;
    private Position from;
    private String promote;

    void setPromote(String promote) {
        this.promote = promote;
    }


    ChessboardController(MainFrameView mainFrameView){
        promote = "D";
        this.mainFrameView = mainFrameView;
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardView = this.mainPanelView.getChessboardView();
        addButtonListener();

    }


    private void addButtonListener(){

        ActionListener buttonListener = actionEvent -> {
            String [] s = actionEvent.getActionCommand().split(" ",2);
            Position position = new Position(Integer.valueOf(s[0]),Integer.valueOf(s[1]));
            ChessPiece figure;

            if( promote.equals("D") ){  //no promotion
                if( from == null ){ // TAKING PIECE
                    figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
                    if( figure.getClass() != EmptySquare.class && isColorOfMovedPieceCorrect(figure.getChessColour()) ){
                        takePiece(position);
                    }
                }else {
                    Move move = new Move(from,position);
                    if( StateOfGame.variant.validateMove(move) ){
                        movePiece(move);
                        checkState();
                    }else{
                        takePieceUndo();
                    }
                }
            }else {
                if( from == null ){
                    figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
                    if( figure.getClass() == Pawn.class && isColorOfMovedPieceCorrect(figure.getChessColour())){
                        takePieceForPromotion(position);
                    }
                }else {
                    ChessPiece newChessPiece;
                    figure = StateOfGame.chessboard.getChessPieceOnPosition(from);
                    switch( promote ){
                        case "Q":
                            newChessPiece = new Queen(figure.getChessColour(),position);
                            break;
                        case "R":
                            newChessPiece = new Rook(figure.getChessColour(),position);
                            break;
                        case "K":
                            newChessPiece = new Knight(figure.getChessColour(),position);
                            break;
                        case "B":
                            newChessPiece = new Bishop(figure.getChessColour(),position);
                            break;
                        default:
                            newChessPiece = new Pawn(figure.getChessColour(),position);
                    }
                    Move move = new Move(from,position,newChessPiece);
                    if( StateOfGame.variant.validateMove(move) ){
                        movePieceWithPromotion(move);
                        checkState();
                    }else{
                        takePieceUndo();
                    }

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

    private void showEndGameDialog(){
        switch (StateOfGame.getStateOfGameplay() ){
            case DRAW:
                JOptionPane.showMessageDialog(mainFrameView, "Game over: draw");
                break;
            case WHITE_WON:
                JOptionPane.showMessageDialog(mainFrameView, "Game over: white player won");
                break;
            case BLACK_WON:
                JOptionPane.showMessageDialog(mainFrameView, "Game over: black player won");
                break;
        }
    }
    private void checkState(){
        System.out.println(StateOfGame.getStateOfGameplay());
        switch (StateOfGame.getStateOfGameplay() ){
            case DRAW:
            case WHITE_WON:
            case BLACK_WON:
                showEndGameDialog();
                restartGame();
                break;
        }
    }
    private void takePieceForPromotion(Position position) {
        chessboardView.highlightPosition(position);
        from = position;
    }
    private void movePieceWithPromotion(Move move){
        chessboardView.highlightPositionUndo(from);
        StateOfGame.variant.changeState(move);
        mainFrameView.updateView();
        from = null;
    }
//    private void takePieceForPromotionUndo(){
//        chessboardView.highlightPositionUndo(from);
//        from = null;
//    }

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
    void takePieceUndo(){
        if( from != null ){
            if( promote.equals("D")){
                chessboardView.highlightPositionUndo(from);
                chessboardView.highlightPossiblePositionsUndo(from);
                from = null;
            }else {// piece for promotion
                chessboardView.highlightPositionUndo(from);
                from = null;
            }
        }
    }

}
