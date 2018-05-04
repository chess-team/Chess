package Chess.View;

import Chess.Model.ChessColour;
import Chess.Model.ChessPieces.ChessPiece;
import Chess.Model.StateOfGame;
import Chess.View.ChessboardView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CapturedPiecesView extends JPanel{

    private JLabel [][]capturedPieces;
    private ChessColour color;
    private Color frameColor;
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private int width=2;
    private int height;
    private Map<Character, ImageIcon> piecesIcons;

    public CapturedPiecesView(ChessColour color){
        super();
        this.color=color;
        this.frameColor=frameColor;
        initCapturedPiecesView();
    }

    private void initCapturedPiecesView(){
        height=StateOfGame.chessboard.getYWidth()+2;
        setLayout(new GridLayout(height,2));
        capturedPieces= new JLabel[height][2];
        for(int i=0; i<height; i++){
            for(int j=0; j<2; j++){
                    capturedPieces[i][j] = new JLabel();
                    capturedPieces[i][j].setIcon(transparentIcon);
                    add(capturedPieces[i][j]);
            }
        }

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


    public void setPiecesIcons(Map<Character, ImageIcon> piecesIcons){
        this.piecesIcons=piecesIcons;
    }

    //TODO
    public void updateCapturedPiecesView(){
        height=StateOfGame.chessboard.getYWidth();
    }

}
