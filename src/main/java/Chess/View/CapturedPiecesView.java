package Chess.View;

import Chess.Model.ChessColour;
import Chess.Model.StateOfGame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CapturedPiecesView extends JPanel{

    private JLabel [][]capturedPieces;
    private ChessColour color;
    private ImageIcon transparentIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private int width;
    private int height;
    private Map<Character, ImageIcon> piecesIcons;

    CapturedPiecesView(ChessColour color){
        super();
        this.color=color;
        initCapturedPiecesView();
    }

    private void initCapturedPiecesView(){
        height=StateOfGame.chessboard.getYWidth()+2;
        width=4;
        setLayout(new GridLayout(height,width));
        capturedPieces= new JLabel[height][width];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
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


    public void updateCapturedPiecesView(){
        List<Character> listOfLabelsOfCapturedPieces= new ArrayList<>(StateOfGame.getListOfLabelsOfCapturedPieces());
        if(color==ChessColour.WHITE){
            int x=0, y=width-1;
            for(Character c: listOfLabelsOfCapturedPieces){
                if(c=='P' || c=='R' || c=='W' || c=='Q' || c=='B' || c=='K') {
                    capturedPieces[x][y].setIcon(piecesIcons.get(c));
                    x++;
                    if(x==height){
                        x=0;
                        y--;
                        if(y<0) y=0;
                    }
                }
            }
        }else{
            int x=0, y=0;
            for(Character c: listOfLabelsOfCapturedPieces){
                if(c=='p' || c=='r' || c=='w' || c=='q' || c=='b' || c=='k') {
                    capturedPieces[x][y].setIcon(piecesIcons.get(Character.toUpperCase(c)));
                    x++;
                    if(x==height){
                        x=0;
                        y=(y+1)%width;
                    }
                }
            }
        }
    }

}
