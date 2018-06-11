package Chess.Controller;

import Chess.Model.*;
import Chess.Model.ArtificialIntelligence.AI;
import Chess.Model.ArtificialIntelligence.RandomAI;
import Chess.Model.ChessPieces.*;
import Chess.Model.Moves.KillFigureMove;
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
    private String selectedCheat;

    ChessboardController(MainFrameView mainFrameView) {
        this.mainFrameView = mainFrameView;
        this.mainPanelView = mainFrameView.getMainPanelView();
        this.chessboardView = this.mainPanelView.getChessboardView();
        player = new RandomAI();
        addButtonListener();
        modeOfGame = "Both";
        this.setPlayFair();
    }

    void setFromToNull() {
        this.from = null;
    }

    void setModeOfGame(String modeOfGame) {
        this.modeOfGame = modeOfGame;
        makeComputerMoves();
    }

    void setSelectedCheat(String selectedCheat) {
        this.selectedCheat = selectedCheat;
    }
    private void setPlayFair(){
        mainFrameView.getMenuBarView().selectPlayFair();
        this.selectedCheat = "pf";
    }

    private void addButtonListener() {
        ActionListener buttonListener = actionEvent -> {
            String[] s = actionEvent.getActionCommand().split(" ", 2);
            Position position = new Position(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
            if( !selectedCheat.equals("pf") ){
                makeCheatMove(position);
                mainPanelView.updateMainPanelView();
                checkState();
                setPlayFair();
            }else if (from == null) {
                takePiece(position);
            } else {
                ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(from);
                Move move = new Move(from, position);
                if (StateOfGame.variant.validateMove(move)) {
                    movePiece(move);
                    makeComputerMoves();
                } else {
                    Move promotionMove = new Move(from, position, new Queen(figure.getChessColour(), position));
                    if (StateOfGame.variant.validateMove(promotionMove)) {
                        makePromotionMove(figure, from, position);
                        makeComputerMoves();
                    } else takePieceUndo();
                }
            }
        };
        chessboardView.setActionListener(buttonListener);
    }

    private void makeCheatMove(Position position){
        System.out.println(position);
        switch (selectedCheat) {
            case "pf":
                break;
            case "kf":
                killFigure(position);
                break;
        }
    }

    private void killFigure(Position position){
        KillFigureMove killFigureMove = new KillFigureMove(position);
        if( StateOfGame.variant.validateMove(killFigureMove) ){
            StateOfGame.variant.changeState(killFigureMove);
        }else {
            JOptionPane.showMessageDialog(mainFrameView, "Bad square");
        }
    }

    private void takePiece(Position position) {
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
        if (figure.getClass() != EmptySquare.class && isColorOfMovedPieceCorrect(figure.getChessColour())) {
            chessboardView.highlightPosition(position);
            chessboardView.highlightPossiblePositions(position);
            from = position;
        }
    }

    private boolean isColorOfMovedPieceCorrect(ChessColour colorOfMovedPiece) {
        switch (StateOfGame.getStateOfGameplay()) {
            case WHITE_MOVE:
                return colorOfMovedPiece == ChessColour.WHITE;
            case BLACK_MOVE:
                return colorOfMovedPiece == ChessColour.BLACK;
        }
        return false;
    }

    void takePieceUndo() {
        if (from != null) {
            chessboardView.updateChessboard();
            from = null;
        }
    }

    private void movePiece(Move move) {
        StateOfGame.variant.changeState(move);
        mainPanelView.updateMainPanelView();
        from = null;
        //checkState();
    }

    private void makePromotionMove(ChessPiece figure, Position from, Position to) {
        Object[] possibleValues = {"Queen", "Bishop", "Knight", "Rook"};
        promote = (String) JOptionPane.showInputDialog(null,
                "Promote pawn to", "Promotion menu",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        System.out.println(promote);
        Move move = getPromotionMove(figure, from, to);
        movePiece(move);
    }

    private Move getPromotionMove(ChessPiece figure, Position from, Position to) {
        ChessPiece newChessPiece;
        switch (promote) {
            case "Queen":
                newChessPiece = new Queen(figure.getChessColour(), to);
                break;
            case "Rook":
                newChessPiece = new Rook(figure.getChessColour(), to);
                break;
            case "Knight":
                newChessPiece = new Knight(figure.getChessColour(), to);
                break;
            case "Bishop":
                newChessPiece = new Bishop(figure.getChessColour(), to);
                break;
            default:
                newChessPiece = new Pawn(figure.getChessColour(), to);
        }
        return new Move(from, to, newChessPiece);
    }


    private void checkState() {
        if (gameOver()) {
            showEndGameDialog();
            restartGame();
        }
    }

    void restartGame() {
        StateOfGame.variant.initializeStateOfGame();
        mainFrameView.updateView();
        makeComputerMoves();
    }

    private void showEndGameDialog() {
        switch (StateOfGame.getStateOfGameplay()) {
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

    void makeComputerMoves() {
        int tmp = 0;
        while (!gameOver() && isComputerTurn()) {
            tmp++;
            StateOfGame.variant.changeState(player.getAIMove());
            mainPanelView.updateMainPanelView();
        }
        if( tmp > 0 )
            System.out.println("Number of AI moves: " + tmp);
        checkState();
    }

    private boolean gameOver() {
        switch (StateOfGame.getStateOfGameplay()) {
            case DRAW:
            case WHITE_WON:
            case BLACK_WON:
                return true;
        }
        return false;
    }

    private boolean isComputerTurn() {
        if (modeOfGame.equals("White")) {
            return StateOfGame.getStateOfGameplay().equals(StateOfGameplay.BLACK_MOVE);
        }
        if (modeOfGame.equals("Black")) {
            return StateOfGame.getStateOfGameplay().equals(StateOfGameplay.WHITE_MOVE);

        }
        return false;
    }

    void undoMove() {
        if (modeOfGame.equals("Both")) {
            StateOfGame.undoMove();
        }
        else {
            while (lastMoveWasComputerMove()) {
                StateOfGame.undoMove();
            }
            StateOfGame.undoMove();
            makeComputerMoves();
        }
        mainPanelView.updateMainPanelView();
    }

    private boolean lastMoveWasComputerMove() {
        if (StateOfGame.historyOfMoves.lastMove() == null)
            return false;
        if (modeOfGame.equals("White")) {
            return StateOfGame.colorOfLastMove().equals(StateOfGameplay.BLACK_MOVE);
        } else if (modeOfGame.equals("Black")) {
            return StateOfGame.colorOfLastMove().equals(StateOfGameplay.WHITE_MOVE);
        }
        return false;
    }

    void switchRotation() {
        chessboardView.switchRotation();
        mainPanelView.updateMainPanelView();
    }

}
