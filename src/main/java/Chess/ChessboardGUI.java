package Chess;

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
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

import static javax.imageio.ImageIO.read;

public class ChessboardGUI extends JPanel {
    private JButton[][] chessboardSquares;
    private Insets buttonMargin = new Insets(0, 0, 0, 0);
    private Map<Character, ImageIcon> lightChessboardIcons = new HashMap();
    private Map<Character, ImageIcon> darkChessboardIcons = new HashMap<>();
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private int height;
    private int width;
    private String labels = "ABCDEFGH";
    private Color frameColor, whiteColor, blackColor;
    private Color highlightColor = new Color(0, 255, 255);

    public ChessboardGUI(int width, int height, ActionListener actionListener, int colorType, int iconsType) {
        super(new GridLayout(width + 2, height + 2));
        this.height = height + 2;
        this.width = width + 2;
        chessboardSquares = new JButton[width][height];
        initChessboardFrame(actionListener, colorType, iconsType);
    }

    public int getChessboardHeight() {
        return height - 2;
    }

    public int getChessboardWidth() {
        return width - 2;
    }

    public Color getFrameColor() {
        return frameColor;
    }

    public Color getWhiteColor() {
        return whiteColor;
    }

    public Color getBlackColor() {
        return blackColor;
    }

    public JButton[][] getChessboardSquares() {
        return chessboardSquares;
    }

    public JButton getChessboardSquare(int i, int j) {
        if (i < 0 || j < 0 || i > StateOfGame.chessboard.getXWidth() - 1 || j > StateOfGame.chessboard.getYWidth() - 1)
            return null;
        return chessboardSquares[i][j];
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

    //simple chessboard
    public void initChessboard(ActionListener actionListener, int colorType, int iconsType) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isFrame(i, j)) {
                    JButton newButton = new JButton();
                    newButton.revalidate();
                    newButton.setIcon(transparentIcon);
                    newButton.setMargin(buttonMargin);
                    newButton.addActionListener(actionListener);
                    newButton.setActionCommand("" + i + j);
                    chessboardSquares[i - 1][j - 1] = newButton;
                    add(chessboardSquares[i - 1][j - 1]);
                } else
                    add(new JPanel());
            }
        }
        setColor(colorType);
        setIcons(iconsType);
    }

    //chessboard with frame and labels
    public void initChessboardFrame(ActionListener actionListener, int colorType, int iconsType) {
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
                    newButton.addActionListener(actionListener);
                    newButton.setActionCommand("" + (i - 1) + " " + (j - 1));
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

    public void setColor() {
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


    public void setIcons(int type) {
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


    private void loadIconsType0() {
        try {
            BufferedImage buf;
            File pic = new File("src/main/resources/blackPawn1.png");
            pic.createNewFile();
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            buf = read(pic);
            Files.setPosixFilePermissions(pic.toPath(), perms);
            darkChessboardIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/blackRook1.png");
            buf = read(pic);
            darkChessboardIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKing1.png");
            buf = read(pic);
            darkChessboardIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/blackQueen1.png");
            buf = read(pic);
            darkChessboardIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/blackBishop1.png");
            buf = read(pic);
            darkChessboardIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKnight1.png");
            buf = read(pic);
            darkChessboardIcons.put('K', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteRook1.png");
            buf = read(pic);
            lightChessboardIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/whitePawn1.png");
            buf = read(pic);
            lightChessboardIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteBishop1.png");
            buf = read(pic);
            lightChessboardIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteQueen1.png");
            buf = read(pic);
            lightChessboardIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKing1.png");
            buf = read(pic);
            lightChessboardIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKnight1.png");
            buf = read(pic);
            lightChessboardIcons.put('K', new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //these icons don't look nice, need to change
    /*private void loadIconsType1() {
        try {
            BufferedImage buf;
            File pic = new File("src/main/resources/blackPawn2.png");
            pic.createNewFile();
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            buf = read(pic);
            Files.setPosixFilePermissions(pic.toPath(), perms);
            darkChessboardIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/blackRook2.png");
            buf = read(pic);
            darkChessboardIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKing2.png");
            buf = read(pic);
            darkChessboardIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/blackQueen2.png");
            buf = read(pic);
            darkChessboardIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/blackBishop2.png");
            buf = read(pic);
            darkChessboardIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/blackKnight2.png");
            buf = read(pic);
            darkChessboardIcons.put('K', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenRook2.png");
            buf = read(pic);
            lightChessboardIcons.put('R', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenPawn2.png");
            buf = read(pic);
            lightChessboardIcons.put('P', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenBishop2.png");
            buf = read(pic);
            lightChessboardIcons.put('B', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenQueen2.png");
            buf = read(pic);
            lightChessboardIcons.put('Q', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenKing2.png");
            buf = read(pic);
            lightChessboardIcons.put('W', new ImageIcon(buf));
            pic = new File("src/main/resources/goldenKnight2.png");
            buf = read(pic);
            lightChessboardIcons.put('K', new ImageIcon(buf));
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
                chessboardSquares[position.x][position.y].setIcon(lightChessboardIcons.get(figure.label));
            } else {
                chessboardSquares[position.x][position.y].setIcon(darkChessboardIcons.get(figure.label));
            }
        } else {
            chessboardSquares[position.x][position.y].setIcon(transparentIcon);
        }
    }

    public void highlightPosition(Position a) {
        chessboardSquares[a.x][a.y].setBackground(highlightColor);
    }

    public void highlightPossiblePositions(ArrayList<Move> list) {
        for (Move move : list) {
            Position to = move.to;
            highlightPosition(to);
        }
    }

    public void highlightPositionUndo(Position a) {
        if (a.x % 2 == a.y % 2)
            chessboardSquares[a.x][a.y].setBackground(whiteColor);
        else
            chessboardSquares[a.x][a.y].setBackground(blackColor);
    }

    public void highlightPossiblePositionsUndo(ArrayList<Move> list) {
        for (Move move : list) {
            Position to = move.to;
            highlightPositionUndo(to);
        }
    }
}

