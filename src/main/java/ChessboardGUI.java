
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
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

    private int height;
    private int width;

    Chessboard chessboard= StateOfGame.chessboard;
    public JButton[][] chessboardSquare;

    public Map<Character,ImageIcon> lightChessboardIcons= new HashMap();
    public Map<Character,ImageIcon> darkChessboardIcons= new HashMap<>();

    public ChessboardGUI(GridLayout g){
        super(g);
        height=chessboard.getYWidth()+2;
        width=chessboard.getXWidth()+2;
        chessboardSquare = new JButton[width-2][height-2];


    }
    Insets buttonMargin = new Insets(0, 0, 0, 0);

    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension prefSize = null;
        Component c = getParent();
        if (c == null) {
            prefSize = new Dimension(
                    (int)d.getWidth(),(int)d.getHeight());
        } else if (c!=null &&
                c.getWidth()>d.getWidth() &&
                c.getHeight()>d.getHeight()) {
            prefSize = c.getSize();
        } else {
            prefSize = d;
        }
        int w = (int) prefSize.getWidth();
        int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        int s = (w>h ? h : w);
        return new Dimension(s,s);
    }

    public void initChessboard(){
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                if(isBorder(i,j)) {  //TODO chessboardBorder
                    JLabel border = new JLabel();
                    border.setBackground(new Color( 139, 69, 19 ));
                    add(border);
                }else {
                    add(chessboardSquare[i-1][j-1]);
                }
            }
        }
    }

    public void setColorType1(){
        for(int i=0; i<chessboard.getXWidth(); i++){
            for(int j=0; j<chessboard.getYWidth(); j++){
                JButton newButton= new JButton();
                newButton.setIcon(transparentIcon);
                newButton.setMargin(buttonMargin);
                if(i%2 == j%2) newButton.setBackground(Color.WHITE);
                else newButton.setBackground(Color.BLACK);
                chessboardSquare[i][j]=newButton;
            }
        }
    }

    public void setColorType2(){
        for(int i=0; i<width-2; i++){
            for(int j=0; j<height-2; j++){
                JButton newButton = new JButton();
                if (i % 2 == j % 2) newButton.setBackground(new Color(255, 255, 224));
                else newButton.setBackground(new Color(205, 133, 63));
                newButton.setMargin(buttonMargin);
                newButton.setIcon(transparentIcon);
                chessboardSquare[i][j] = newButton;
            }
        }
    }

    public void setIconsType1() {
        loadIconsType1();
        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                Position position = new Position(i, j);
                ChessPiece figure = chessboard.getChessPieceOnPosition(position);
                if (figure.getClass() != EmptySquare.class) {
                    if (figure.getChessColour() == ChessColour.WHITE) {
                        chessboardSquare[i][j].setIcon(lightChessboardIcons.get(figure.label));
                    } else {
                        chessboardSquare[i][j].setIcon(darkChessboardIcons.get(figure.label));
                    }
                }
            }
        }
    }


    private void loadIconsType1(){
        try {
            BufferedImage buf;
            File pic = new File("src/main/resources/blackPawn1.png");
            pic.createNewFile();
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            buf = read(pic);
            Files.setPosixFilePermissions(pic.toPath(), perms);
            darkChessboardIcons.put('P',new ImageIcon(buf));
            pic = new File("src/main/resources/blackRook1.png");
            buf=read(pic);
            darkChessboardIcons.put('R',new ImageIcon(buf));
            pic = new File("src/main/resources/blackKing1.png");
            buf=read(pic);
            darkChessboardIcons.put('W',new ImageIcon(buf));
            pic = new File("src/main/resources/blackQueen1.png");
            buf=read(pic);
            darkChessboardIcons.put('Q',new ImageIcon(buf));
            pic = new File("src/main/resources/blackBishop1.png");
            buf=read(pic);
            darkChessboardIcons.put('B',new ImageIcon(buf));
            pic = new File("src/main/resources/blackKnight1.png");
            buf=read(pic);
            darkChessboardIcons.put('K',new ImageIcon(buf));
            pic = new File("src/main/resources/whiteRook1.png");
            buf=read(pic);
            lightChessboardIcons.put('R',new ImageIcon(buf));
            pic = new File("src/main/resources/whitePawn1.png");
            buf=read(pic);
            lightChessboardIcons.put('P',new ImageIcon(buf));
            pic = new File("src/main/resources/whiteBishop1.png");
            buf=read(pic);
            lightChessboardIcons.put('B',new ImageIcon(buf));
            pic = new File("src/main/resources/whiteQueen1.png");
            buf=read(pic);
            lightChessboardIcons.put('Q',new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKing1.png");
            buf=read(pic);
            lightChessboardIcons.put('W',new ImageIcon(buf));
            pic = new File("src/main/resources/whiteKnight1.png");
            buf=read(pic);
            lightChessboardIcons.put('K',new ImageIcon(buf));
            pic = new File("src/main/resources/whitePawn1.png");
            buf=read(pic);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean isBorder(int i, int j) {
        return i == 0 || i == width - 1 || j == 0 || j == height - 1;
    }
}


