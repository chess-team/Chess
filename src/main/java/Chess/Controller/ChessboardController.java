package Chess.Controller;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.*;
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
    private String promote;

    public void setPromote(String promote) {
        this.promote = promote;
    }


    public ChessboardController(MainFrameView mainFrameView){
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

            if( promote == "D"){  //no promotion
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
                        takePieceForPromotionUndo();
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
    private void takePieceForPromotionUndo(){
        chessboardView.highlightPositionUndo(from);
        from = null;
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
