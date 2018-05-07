package Chess.View;

import Chess.Model.*;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.ChessPieces.EmptySquare;
import Chess.Model.Moves.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
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
    private Color highlightRedColor= new Color( 220, 20, 60 );
    public ChessboardView(int width, int height, int colorType, int iconsType) {
        super(new GridLayout(width + 2, height + 2));
        this.height = height + 2;
        this.width = width + 2;
        chessboardSquares = new JButton[width][height];
        initChessboardFrame(colorType, iconsType);
    }

    public Map<Character, ImageIcon> getWhitePiecesIcons() {
        return whitePiecesIcons;
    }

    public Map<Character, ImageIcon> getBlackPiecesIcons() {
        return blackPiecesIcons;
    }

    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension prefSize;
        Component c = getParent();
        if (c == null) {
            prefSize = new Dimension(
                    (int) d.getWidth(), (int) d.getHeight());
        } else if (c.getWidth() > d.getWidth() &&
                c.getHeight() > d.getHeight()) {
            prefSize = c.getSize();
        } else {
            prefSize = d;
        }
        int w = (int) prefSize.getWidth();
        int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        int s = (w > h ? h : w);
        return new Dimension(s, s);
    }


    //chessboard with frame and labels
    private void initChessboardFrame(int colorType, int iconsType) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isFrame(i, j)) {
                    JPanel frame = new JPanel();
                    frame.setBackground(frameColor);
                    if (i == 0 && j > 0 && j < width - 1) {
                        JLabel label = new JLabel("" + j);
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        frame.setLayout(new GridBagLayout());
                        frame.add(label);
                    }
                    if (j == 0 && i > 0 && i < height - 1) {
                        String labels = "ABCDEFGH";
                        JLabel label = new JLabel(labels.substring(i - 1, i));
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        frame.setLayout(new GridBagLayout());
                        frame.add(label);
                    }
                    add(frame);
                } else {
                    JButton newButton = new JButton();
                    newButton.revalidate();
                    newButton.setIcon(transparentIcon);
                    newButton.setMargin(buttonMargin);
                    chessboardSquares[i - 1][j - 1] = newButton;
                    add(chessboardSquares[i - 1][j - 1]);
                }
            }
        }
        setColor(colorType);
        setIcons(iconsType);
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


    private void setIcons(int type) {
        switch (type) {
            case 0:
                loadIconsType0();
                break;
            default:
                loadIconsType0();
                break;
        }
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
                chessboardSquares[i][j].setActionCommand("" + i + " " + j);
            }
        }
    }




    private void loadIconsType0() {
        try {
            BufferedImage buf;
            File pic = new File("src/main/resources/blackPawn1.png");
            buf = read(pic);
            blackPiecesIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/blackRook1.png");
            buf = read(pic);
            blackPiecesIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKing1.png");
            buf = read(pic);
            blackPiecesIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/blackQueen1.png");
            buf = read(pic);
            blackPiecesIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/blackBishop1.png");
            buf = read(pic);
            blackPiecesIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKnight1.png");
            buf = read(pic);
            blackPiecesIcons.put('K', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteRook1.png");
            buf = read(pic);
            whitePiecesIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/whitePawn1.png");
            buf = read(pic);
            whitePiecesIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteBishop1.png");
            buf = read(pic);
            whitePiecesIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteQueen1.png");
            buf = read(pic);
            whitePiecesIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKing1.png");
            buf = read(pic);
            whitePiecesIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKnight1.png");
            buf = read(pic);
            whitePiecesIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //these icons don't look nice, need to change
    /*private void loadIconsType1() {
        try {
            BufferedImage buf;
            File pic = new File("src/main/resources/blackPawn2.png");
            buf = read(pic);
            Files.setPosixFilePermissions(pic.toPath(), perms);
            blackPiecesIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/blackRook2.png");
            buf = read(pic);
            blackPiecesIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKing2.png");
            buf = read(pic);
            blackPiecesIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/blackQueen2.png");
            buf = read(pic);
            blackPiecesIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/blackBishop2.png");
            buf = read(pic);
            blackPiecesIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKnight2.png");
            buf = read(pic);
            blackPiecesIcons.put('K', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenRook2.png");
            buf = read(pic);
            whitePiecesIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenPawn2.png");
            buf = read(pic);
            whitePiecesIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenBishop2.png");
            buf = read(pic);
            whitePiecesIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenQueen2.png");
            buf = read(pic);
            whitePiecesIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenKing2.png");
            buf = read(pic);
            whitePiecesIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenKnight2.png");
            buf = read(pic);
            whitePiecesIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private boolean isFrame(int i, int j) {
        return i == 0 || i == width - 1 || j == 0 || j == height - 1;
    }

    public void updateIcon(Position position) {
        ChessPiece figure = StateOfGame.chessboard.getChessPieceOnPosition(position);
        if (figure.getClass() != EmptySquare.class) {
            if (figure.getChessColour() == ChessColour.WHITE) {
                chessboardSquares[position.x][position.y].setIcon(whitePiecesIcons.get(figure.label));
            } else {
                chessboardSquares[position.x][position.y].setIcon(blackPiecesIcons.get(figure.label));
            }
        } else {
            chessboardSquares[position.x][position.y].setIcon(transparentIcon);
        }
    }

    private void updateColor(Position a){
        if (a.x % 2 == a.y % 2)
            chessboardSquares[a.x][a.y].setBackground(whiteColor);
        else
            chessboardSquares[a.x][a.y].setBackground(blackColor);
    }

    public void highlightPosition(Position a) {
        chessboardSquares[a.x][a.y].setBackground(highlightColor);
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

    private void highlightRedPosition(Position a){
        chessboardSquares[a.x][a.y].setBackground(highlightRedColor);
    }

    private void highlightIfKingUnderAttack(){
        Position kingPosition=ChessUtil.getKingPosition(ChessColour.WHITE);
        ArrayList<Position> positionsThatAttacksTheKing=ChessUtil.getPositionsThatAttacksTheKing(kingPosition);
        if(!positionsThatAttacksTheKing.isEmpty()){
            highlightRedPosition(kingPosition);
            for(Position a: positionsThatAttacksTheKing){
                highlightRedPosition(a);
            }
            return;
        }kingPosition=ChessUtil.getKingPosition(ChessColour.BLACK);
        positionsThatAttacksTheKing=ChessUtil.getPositionsThatAttacksTheKing(kingPosition);
        if(!positionsThatAttacksTheKing.isEmpty()){
            highlightRedPosition(kingPosition);
            for(Position a: positionsThatAttacksTheKing){
                highlightRedPosition(a);
            }
        }
    }

    public void updateChessboard() {

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                updateIcon(new Position(i, j));
                updateColor(new Position(i,j));
            }
        }
        highlightIfKingUnderAttack();
    }

}

