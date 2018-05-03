package Chess.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuBarView extends JMenuBar {

    private JMenu colorMenu;
    private JRadioButtonMenuItem [] colorButtons;
    private JMenu optionsMenu;
    private JMenuItem newGame, undoLastMove;

    MenuBarView(){
        super();
        initOptionsMenu();
        initColorMenu();
    }



    void initOptionsMenu(){
        optionsMenu = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        optionsMenu.add(newGame);
        undoLastMove = new JMenuItem("Undo Last Move");
        optionsMenu.add(undoLastMove);
        this.add(optionsMenu);
    }



    void initColorMenu(){
        colorButtons = new JRadioButtonMenuItem[3];
        for( int i = 0 ; i < 3; i++ ){
            colorButtons[i] = new JRadioButtonMenuItem();
            colorButtons[i].setActionCommand(String.valueOf(i));
        }
        setColorButtonsIcons();
        colorButtons[0].setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        colorMenu = new JMenu("Color");
        for( int i = 0 ; i < 3; i++ ){
            buttonGroup.add(colorButtons[i]);
            colorMenu.add(colorButtons[i]);
        }
        this.add(colorMenu);
    }

    public void addColorListener(ActionListener actionListener){
        for( int i = 0 ; i < 3; i++ ){
            colorButtons[i].addActionListener(actionListener);
        }
    }
    public void addNewGameListener(ActionListener actionListener){
        newGame.addActionListener(actionListener);
    }
    public void addUndoLastMoveListener(ActionListener actionListener){ undoLastMove.addActionListener(actionListener);}

    private void setColorButtonsIcons(){
        try {
            BufferedImage buf;
            buf= ImageIO.read(new File("src/main/resources/colorType0.png"));
            colorButtons[0].setIcon(new ImageIcon(buf));
            buf= ImageIO.read(new File("src/main/resources/colorType1.png"));
            colorButtons[1].setIcon(new ImageIcon(buf));
            buf= ImageIO.read(new File("src/main/resources/colorType2.png"));
            colorButtons[2].setIcon(new ImageIcon(buf));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
