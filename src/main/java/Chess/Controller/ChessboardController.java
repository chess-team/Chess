package Chess.Controller;

import Chess.Model.*;
import Chess.Model.ArtificialIntelligence.AI;
import Chess.Model.ArtificialIntelligence.anyMoveAI;
import Chess.Model.ChessPieces.*;
import Chess.Model.Moves.Move;
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
    private AI player;
    private String modeOfGame;

    void setPromote(String promote) {
        this.promote = promote;
    }
    void setFromToNull(){ this.from = null; }
    void setModeOfGame( String modeOfGame ){ this.modeOfGame = modeOfGame; }


    ChessboardController(MainFrameView mainFrameView){
        promote = "D";
        modeOfGame = "PvP";
        this.mainFrameView = mainFrameView;
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardView = this.mainPanelView.getChessboardView();
        player = new anyMoveAI();
        addButtonListener();

    }


    private void addButtonListener(){

        ActionListener buttonListener = actionEvent -> {
            String [] s = actionEvent.getActionCommand().split(" ",2);
            Position position = new Position(Integer.valueOf(s[0]),Integer.valueOf(s[1]));
            if( from == null ){
                takePiece(position);
            }else {
                ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(from);
                Move move = getMove(figure,from,position);
                if( StateOfGame.variant.validateMove(move) ){
                    movePiece(move);
                    if( modeOfGame.equals("PvC") && !gameOver() ){
                        StateOfGame.variant.changeState(player.getAIMove());
                        mainPanelView.updateMainPanelView();
                    }
                    checkState();
                }else{
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

    public void restartGame() {
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
        if( gameOver() ){
            showEndGameDialog();
            restartGame();
        }
    }
    private boolean gameOver(){
        switch (StateOfGame.getStateOfGameplay() ){
            case DRAW:
            case WHITE_WON:
            case BLACK_WON:
                return true;
        }
        return false;
    }
    private Move getMove(ChessPiece figure, Position from, Position position ){
        Move move = new Move(from,position);
        if( !promote.equals("D") ){
            ChessPiece newChessPiece;
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
            move = new Move(from,position,newChessPiece);
        }
        return move;
    }
    private void takePiece(Position position){
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
        if( figure.getClass() != EmptySquare.class && isColorOfMovedPieceCorrect(figure.getChessColour()) ){
            boolean highlightPromotionMoves = true;
            if( promote.equals("D") )
                highlightPromotionMoves = false;
            chessboardView.highlightPosition(position);
            chessboardView.highlightPossiblePositions(position, highlightPromotionMoves);
            from = position;
        }
    }
    private void movePiece( Move move ) {
        StateOfGame.variant.changeState(move);
        mainPanelView.updateMainPanelView();

        from = null;
    }
    void takePieceUndo(){
        if( from != null ){
            chessboardView.updateChessboard();
            from = null;
        }
    }

    void switchRotation(){
        chessboardView.switchRotation();
        mainPanelView.updateMainPanelView();
    }

    void undoMove() {
        if (modeOfGame.equals("PvP")) StateOfGame.undoMove();
        else {
            StateOfGame.undoMove();
            StateOfGame.undoMove();
        }
        mainPanelView.updateMainPanelView();
    }

}
