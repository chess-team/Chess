package Chess.View;

import Chess.Model.*;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.Moves.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import static javax.imageio.ImageIO.read;

public class ChessboardView extends JPanel {
    private JButton[][] chessboardSquares;
    private Insets buttonMargin = new Insets(0, 0, 0, 0);
    private Map<Character, ImageIcon> whitePiecesIcons = new HashMap<>();
    private Map<Character, ImageIcon> blackPiecesIcons = new HashMap<>();
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private int height;
    private int width;
    private Color frameColor, whiteColor, blackColor;
    private Color highlightColor = new Color(0, 255, 255);
    private Color highlightRedColor = new Color(220, 20, 60);
    private boolean rotated = false, rotation=false;
    private JLabel[] digitLabels;
    private JLabel[] letterLabels;

    ChessboardView() {
        super();
        height = StateOfGame.chessboard.getXWidth() + 2;
        width = StateOfGame.chessboard.getYWidth() + 2;
        setLayout(new GridLayout(width, height));
        chessboardSquares = new JButton[width - 2][height - 2];
        digitLabels = new JLabel[height - 2];
        letterLabels = new JLabel[width - 2];
        initChessboardFrame();
    }

    public Map<Character, ImageIcon> getWhitePiecesIcons() {
        return whitePiecesIcons;
    }

    public Map<Character, ImageIcon> getBlackPiecesIcons() {
        return blackPiecesIcons;
    }


    //chessboard with frame and labels
    private void initChessboardFrame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isFrame(i, j)) {
                    JPanel frame = new JPanel();
                    frame.setBackground(frameColor);
                    if (j == 0 && i > 0 && i < height - 1) {
                        JLabel label = new JLabel("" + (9-i));
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        frame.setLayout(new GridBagLayout());
                        frame.add(label);
                        digitLabels[i - 1] = label;
                    }
                    if (i == height-1 && j > 0 && j < width - 1) {
                        String labels = "ABCDEFGH";
                        JLabel label = new JLabel(labels.substring(j - 1, j));
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        frame.setLayout(new GridBagLayout());
                        frame.add(label);
                        letterLabels[j - 1] = label;
                    }
                    add(frame);
                } else {
                    JButton newButton = new JButton();
                    newButton.setOpaque(true);
                    newButton.setBorderPainted(false);
                    newButton.setFocusable(false);
                    newButton.revalidate();
                    newButton.setIcon(transparentIcon);
                    newButton.setMargin(buttonMargin);
                    chessboardSquares[i - 1][j - 1] = newButton;
                    add(chessboardSquares[i - 1][j - 1]);
                }
            }
        }
        setColor(0);
        setIcons();
    }

    public void setColor(int type) {
        switch (type) {
            case 0: {
                whiteColor = Color.WHITE;
                blackColor = Color.BLACK;
                frameColor = new Color(128, 128, 128);
                break;
            }
            case 1: {
                whiteColor = new Color(255, 255, 224);
                blackColor = new Color(205, 133, 63);
                frameColor = new Color(139, 69, 19);
                break;
            }
            case 2: {
                whiteColor = new Color(224, 255, 255);
                blackColor = new Color(70, 138, 180);
                frameColor = new Color(128, 128, 128);
                break;
            }
            default: {
                whiteColor = Color.WHITE;
                blackColor = Color.BLACK;
                frameColor = new Color(128, 128, 128);
                break;
            }
        }
        setColor();

    }

    private void setColor() {
        setBackground(frameColor);
        for (int i = 0; i < StateOfGame.chessboard.getXWidth(); i++) {
            for (int j = 0; j < StateOfGame.chessboard.getYWidth(); j++) {
                JButton square = chessboardSquares[i][j];
                if (i % 2 == j % 2) square.setBackground(whiteColor);
                else square.setBackground(blackColor);
                chessboardSquares[i][j] = square;
            }
        }
    }


    private void setIcons() {
                loadIconsType0();

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                Position position = new Position(i, j);
                updateIcon(position);
            }
        }
    }

    public void setActionListener(ActionListener actionListener) {
        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                chessboardSquares[i][j].addActionListener(actionListener);
                chessboardSquares[i][j].setActionCommand("" + j + " " + (7 - i));
            }
        }
    }


    private void loadIconsType0() {
        try {
            BufferedImage buf;
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackPawn1.png"));
            blackPiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackRook1.png"));
            blackPiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKing1.png"));
            blackPiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackQueen1.png"));
            blackPiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackBishop1.png"));
            blackPiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKnight1.png"));
            blackPiecesIcons.put('K', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteRook1.png"));
            whitePiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whitePawn1.png"));
            whitePiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteBishop1.png"));
            whitePiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteQueen1.png"));
            whitePiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKing1.png"));
            whitePiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKnight1.png"));
            whitePiecesIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadIconsType2() {
        try {
            BufferedImage buf;
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackPawn1small.png"));
            blackPiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackRook1small.png"));
            blackPiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKing1small.png"));
            blackPiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackQueen1small.png"));
            blackPiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackBishop1small.png"));
            blackPiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKnight1small.png"));
            blackPiecesIcons.put('K', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteRook1small.png"));
            whitePiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whitePawn1small.png"));
            whitePiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteBishop1small.png"));
            whitePiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteQueen1small.png"));
            whitePiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKing1small.png"));
            whitePiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKnight1small.png"));
            whitePiecesIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadIconsType1() {
        try {
            BufferedImage buf;
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackPawn1medium.png"));
            blackPiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackRook1medium.png"));
            blackPiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKing1medium.png"));
            blackPiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackQueen1medium.png"));
            blackPiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackBishop1medium.png"));
            blackPiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/blackKnight1medium.png"));
            blackPiecesIcons.put('K', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteRook1medium.png"));
            whitePiecesIcons.put('R', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whitePawn1medium.png"));
            whitePiecesIcons.put('P', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteBishop1medium.png"));
            whitePiecesIcons.put('B', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteQueen1medium.png"));
            whitePiecesIcons.put('Q', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKing1medium.png"));
            whitePiecesIcons.put('W', new ImageIcon(buf));
            buf = read(ChessboardView.class.getResourceAsStream("/icons/whiteKnight1medium.png"));
            whitePiecesIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFrame(int i, int j) {
        return i == 0 || i == width - 1 || j == 0 || j == height - 1;
    }

    public void updateIcon(Position position) {
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
        if (figure.getClass() != EmptySquare.class) {
            if (figure.getChessColour() == ChessColour.WHITE) {
                if (rotated)
                    chessboardSquares[position.y][7 - position.x].setIcon(whitePiecesIcons.get(figure.label));
                else
                    chessboardSquares[7 - position.y][position.x].setIcon(whitePiecesIcons.get(figure.label));
            } else {
                if (rotated)
                    chessboardSquares[position.y][7 - position.x].setIcon(blackPiecesIcons.get(figure.label));
                else
                    chessboardSquares[7 - position.y][position.x].setIcon(blackPiecesIcons.get(figure.label));
            }
        } else {
            if (rotated) chessboardSquares[position.y][7 - position.x].setIcon(transparentIcon);
            else
                chessboardSquares[7 - position.y][position.x].setIcon(transparentIcon);
        }
    }

    private void updateColor(Position a) {
        if (a.x % 2 == a.y % 2)
            chessboardSquares[a.y][a.x].setBackground(whiteColor);
        else
            chessboardSquares[a.y][a.x].setBackground(blackColor);
    }

    public void highlightPosition(Position a) {

        if (rotated) chessboardSquares[a.y][7 - a.x].setBackground(highlightColor);
        else chessboardSquares[7 - a.y][a.x].setBackground(highlightColor);
    }

    private void highlightPossiblePositions(ArrayList<Move> list) {
        for (Move move : list) {
            Position to = move.to;
            highlightPosition(to);
        }
    }

    public void highlightPossiblePositions(Position a) {
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(a);
        highlightPossiblePositions(figure.listOfPossibleMoves());
    }

    private void highlightRedPosition(Position a) {
        if (rotated) chessboardSquares[a.y][7 - a.x].setBackground(highlightRedColor);
        else chessboardSquares[7 - a.y][a.x].setBackground(highlightRedColor);
    }

    private void highlightIfKingUnderAttack() {
        Position kingPosition = ChessUtil.getKingPosition(ChessColour.WHITE);
        ArrayList<Position> positionsThatAttacksTheKing = ChessUtil.getPositionsThatAttacksTheKing(kingPosition);
        if (!positionsThatAttacksTheKing.isEmpty()) {
            highlightRedPosition(kingPosition);
            for (Position a : positionsThatAttacksTheKing) {
                highlightRedPosition(a);
            }
        }
        kingPosition = ChessUtil.getKingPosition(ChessColour.BLACK);
        positionsThatAttacksTheKing = ChessUtil.getPositionsThatAttacksTheKing(kingPosition);
        if (!positionsThatAttacksTheKing.isEmpty()) {
            highlightRedPosition(kingPosition);
            for (Position a : positionsThatAttacksTheKing) {
                highlightRedPosition(a);
            }
        }
    }

    public void updateChessboard() {
        if(rotation){
            if((rotated && StateOfGame.getStateOfGameplay()==StateOfGameplay.WHITE_MOVE )
                    || (!rotated && StateOfGame.getStateOfGameplay()==StateOfGameplay.BLACK_MOVE)) rotate();
        }else if(rotated) rotate();
        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                updateIcon(new Position(i, j));
                updateColor(new Position(i, j));
            }
        }
        highlightIfKingUnderAttack();
    }

    private void rotate(){
        for (int i = 0; i < height - 2; i++) {
            for (int j = 0; j < width - 2; j++) {
                if (rotated) chessboardSquares[i][j].setActionCommand(j + " " + (7 - i));
                else chessboardSquares[i][j].setActionCommand((7 - j) + " " + i);
            }
        }
        for(int i=0; i<height-2; i++){
            JLabel frameLabel;
            if(!rotated) {
                frameLabel= new JLabel(""+(i+1));
            }else{
                frameLabel=new JLabel(""+(8-i));
            }
            frameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
            digitLabels[i] = frameLabel;
        }
        String rotatedLabels = "HGFEDCBA", labels = "ABCDEFGH";
        for (int i = 1; i < width - 1; i++) {
            JLabel label;
            if (rotated) {
                label = new JLabel(labels.substring(i - 1, i));
            } else {
                label = new JLabel(rotatedLabels.substring(i - 1, i));
            }
            label.setFont(new Font("Dialog", Font.BOLD, 18));
            letterLabels[i - 1] = label;
        }
        rotated=!rotated;

    }

    public void switchRotation(){
        rotation=!rotation;
    }

    public void scale(int type) {
        if (type == 0) {
            loadIconsType0();
            for (int i = 0; i < height - 2; i++) digitLabels[i].setFont(new Font("Dialog", Font.BOLD, 18));
            for (int i = 0; i < width - 2; i++) letterLabels[i].setFont(new Font("Dialog", Font.BOLD, 18));
        } else if (type == 1) {
            loadIconsType1();
            for (int i = 0; i < height - 2; i++) digitLabels[i].setFont(new Font("Dialog", Font.BOLD, 16));
            for (int i = 0; i < width - 2; i++) letterLabels[i].setFont(new Font("Dialog", Font.BOLD, 16));
        } else {
            loadIconsType2();
            for (int i = 0; i < height - 2; i++) digitLabels[i].setFont(new Font("Dialog", Font.BOLD, 10));
            for (int i = 0; i < width - 2; i++) letterLabels[i].setFont(new Font("Dialog", Font.BOLD, 10));
        }
    }

}

