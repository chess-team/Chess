package Chess.View;

import Chess.Model.GameVariants.ListOfGameVariants;
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
    private JRadioButtonMenuItem [] variantButtons;
    private JMenu modeOfGameMenu;
    private JRadioButtonMenuItem PvP, PvC;
    private JMenu promoteMenu;
    private JRadioButtonMenuItem promoteToBishop, promoteToKnight, promoteToQueen, promoteToRook, dontPromote;
    private ListOfGameVariants variants = new ListOfGameVariants();


    MenuBarView(){
        super();
        initOptionsMenu();
        initVariantOfGameMenu();
        initModeOfGameMenu();
        initColorMenu();
        initPromoteMenu();
    }

    private void initModeOfGameMenu(){
        PvP = new JRadioButtonMenuItem("Player vs Player");
        PvP.setActionCommand("PvP");
        PvC = new JRadioButtonMenuItem("Player vs Computer");
        PvC.setActionCommand("PvC");
        PvP.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        modeOfGameMenu = new JMenu("Mode of Game");
        buttonGroup.add(PvP);
        modeOfGameMenu.add(PvP);
        buttonGroup.add(PvC);
        modeOfGameMenu.add(PvC);
        this.add(modeOfGameMenu);
    }

    private void initPromoteMenu(){
        promoteMenu = new JMenu("Promote pawn");
        dontPromote = new JRadioButtonMenuItem("Don't promote");
        dontPromote.setActionCommand("D");
        dontPromote.setSelected(true);
        promoteToBishop = new JRadioButtonMenuItem("Promote to Bishop");
        promoteToBishop.setActionCommand("B");
        promoteToKnight = new JRadioButtonMenuItem("Promote to Knight");
        promoteToKnight.setActionCommand("K");
        promoteToQueen = new JRadioButtonMenuItem("Promote to Queen");
        promoteToQueen.setActionCommand("Q");
        promoteToRook = new JRadioButtonMenuItem("Promote to Rook");
        promoteToRook.setActionCommand("R");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dontPromote);
        promoteMenu.add(dontPromote);
        buttonGroup.add(promoteToQueen);
        promoteMenu.add(promoteToQueen);
        buttonGroup.add(promoteToBishop);
        promoteMenu.add(promoteToBishop);
        buttonGroup.add(promoteToKnight);
        promoteMenu.add(promoteToKnight);
        buttonGroup.add(promoteToRook);
        promoteMenu.add(promoteToRook);
        this.add(promoteMenu);
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
        variantButtons = new JRadioButtonMenuItem[ variants.getNamesOfVariants().size() ];
        for( int i = 0; i < variants.getNamesOfVariants().size(); i++ ){
            variantButtons[i] = new JRadioButtonMenuItem(variants.getNamesOfVariants().get(i));
        }
        variantButtons[0].setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        for( int i = 0; i < variants.getNamesOfVariants().size(); i++ ){
            buttonGroup.add(variantButtons[i]);
            variantOfGameMenu.add(variantButtons[i]);
        }

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
        for( int i = 0; i < variants.getNamesOfVariants().size(); i++ ){
            variantButtons[i].addActionListener(actionListener);
        }
    }

    public void addModeOfGameListerer( ActionListener actionListener ){
        PvP.addActionListener(actionListener);
        PvC.addActionListener(actionListener);
    }

    public void addPromoteListener( ActionListener actionListener ){
        dontPromote.addActionListener(actionListener);
        promoteToRook.addActionListener(actionListener);
        promoteToQueen.addActionListener(actionListener);
        promoteToKnight.addActionListener(actionListener);
        promoteToBishop.addActionListener(actionListener);
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