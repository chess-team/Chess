
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static javax.imageio.ImageIO.read;

public class ChessboardGUI extends JPanel {
    public JButton[][] chessboardSquare;
    public Map<Character, ImageIcon> lightChessboardIcons = new HashMap();
    public Map<Character, ImageIcon> darkChessboardIcons = new HashMap<>();
    
    Chessboard chessboard = StateOfGame.chessboard;
    Insets buttonMargin = new Insets(0, 0, 0, 0);
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private int height;
    private int width;
    private String labels = "ABCDEFGH";
    private Color frameColor;

    public ChessboardGUI(GridLayout g) {
        super(g);
        height = g.getRows();
        width = g.getColumns();
        chessboardSquare = new JButton[width - 2][height - 2];
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
    public void initChessboard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isFrame(i, j))
                    add(chessboardSquare[i - 1][j - 1]);
                else
                    add(new JPanel());
            }
        }
    }

    //chessboard with frame and labels
    public void initChessboardFrame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isFrame(i, j)) {
                    JPanel border = new JPanel();
                    border.setBackground(frameColor);
                    if (i == 0 && j > 0 && j < width - 1) {
                        JLabel label = new JLabel("" + j);
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        border.setLayout(new GridBagLayout());
                        border.add(label);
                        //add(label);
                    }
                    if (j == 0 && i > 0 && i < height - 1) {
                        JLabel label = new JLabel(labels.substring(i - 1, i));
                        label.setFont(new Font("Dialog", Font.BOLD, 18));
                        border.setLayout(new GridBagLayout());
                        border.add(label);
                        //add(label);
                    }
                    add(border);
                } else {
                    add(chessboardSquare[i - 1][j - 1]);
                }
            }
        }
    }

    public void setColor(int type) {
        Color color1, color2;
        switch (type) {
            case 0: {
                color1 = Color.WHITE;
                color2 = Color.BLACK;
                frameColor = new Color(128, 128, 128);
                break;
            }
            case 1: {
                color1 = new Color(255, 255, 224);
                color2 = new Color(205, 133, 63);
                frameColor = new Color(139, 69, 19);
                break;
            }
            case 2: {
                color1 = new Color(224, 255, 255);
                color2 = new Color(70, 138, 180);
                frameColor = new Color(128, 128, 128);
                break;
            }
            default: {
                color1 = Color.WHITE;
                color2 = Color.BLACK;
                frameColor = new Color(128, 128, 128);
                break;
            }
        }
        setColor(color1, color2);

    }

    public void setColor(Color color1, Color color2) {
        setBackground(frameColor);
        for (int i = 0; i < chessboard.getXWidth(); i++) {
            for (int j = 0; j < chessboard.getYWidth(); j++) {
                JButton newButton = new JButton();
                newButton.revalidate();
                newButton.setIcon(transparentIcon);
                newButton.setMargin(buttonMargin);
                if (i % 2 == j % 2) newButton.setBackground(color1);
                else newButton.setBackground(color2);
                chessboardSquare[i][j] = newButton;
            }
        }
    }


    public void setIcons(int type) {
        switch (type) {
            case 0:
                loadIconsType0();
            default:
                loadIconsType0();
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

    private boolean isFrame(int i, int j) {
        return i == 0 || i == width - 1 || j == 0 || j == height - 1;
    }

    public void updateIcon(Position position) {
        ChessPiece figure = chessboard.getChessPieceOnPosition(position);
        if (figure.getClass() != EmptySquare.class) {
            if (figure.getChessColour() == ChessColour.WHITE) {
                chessboardSquare[position.x][position.y].setIcon(lightChessboardIcons.get(figure.label));
            } else {
                chessboardSquare[position.x][position.y].setIcon(darkChessboardIcons.get(figure.label));
            }
        } else {
            chessboardSquare[position.x][position.y].setIcon(transparentIcon);
        }
    }
}


