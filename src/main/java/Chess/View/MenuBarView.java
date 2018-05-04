package Chess.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuBarView extends JMenuBar {

    private JMenu colorMenu;
    private JRadioButtonMenuItem [] colorButtons;
    private JMenu optionsMenu;
    private JMenuItem newGame, undoLastMove;
    private JMenu variantOfGameMenu;
    private JRadioButtonMenuItem classicChess, chess960;

    MenuBarView(){
        super();
        initOptionsMenu();
        initVariantOfGameMenu();
        initColorMenu();
    }


    private void initOptionsMenu(){
        optionsMenu = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        optionsMenu.add(newGame);
        undoLastMove = new JMenuItem("Undo Last Move");
        optionsMenu.add(undoLastMove);
        this.add(optionsMenu);
    }

    private void initVariantOfGameMenu(){
        variantOfGameMenu = new JMenu("Game Variants");
        classicChess = new JRadioButtonMenuItem("Classic Chess");
        //classicChess.setActionCommand("1");
        classicChess.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        chess960 = new JRadioButtonMenuItem("Chess 960");
        //chess960.setActionCommand("2");
        buttonGroup.add(classicChess);
        variantOfGameMenu.add(classicChess);
        buttonGroup.add(chess960);
        variantOfGameMenu.add(chess960);
        this.add(variantOfGameMenu);

    }

    private void initColorMenu(){
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
    public void addVariantOfGameListener(ActionListener actionListener){
        classicChess.addActionListener(actionListener);
        chess960.addActionListener(actionListener);
    }

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
