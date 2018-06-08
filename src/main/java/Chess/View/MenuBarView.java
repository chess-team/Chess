package Chess.View;

import Chess.Model.GameVariants.ListOfGameVariants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuBarView extends JMenuBar {

    private JRadioButtonMenuItem[] colorButtons;
    private JMenuItem newGame, undoLastMove;
    private JCheckBox rotation;
    private JRadioButtonMenuItem[] variantButtons;
    private JRadioButtonMenuItem Black, White, Both;
    private ListOfGameVariants variants = new ListOfGameVariants();

    MenuBarView() {
        super();
        initOptionsMenu();
        initChoosePlayerMenu();
        initVariantOfGameMenu();
        initColorMenu();
    }

    private void initOptionsMenu() {
        JMenu optionsMenu = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        optionsMenu.add(newGame);
        undoLastMove = new JMenuItem("Undo Last Move");
        optionsMenu.add(undoLastMove);
        rotation = new JCheckBox("Rotation");
        rotation.setHorizontalTextPosition(SwingConstants.LEFT);
        optionsMenu.add(rotation);
        this.add(optionsMenu);
    }

    private void initChoosePlayerMenu() {

        Both = new JRadioButtonMenuItem("Both players");
        Both.setActionCommand("Both");
        Both.setSelected(true);
        White = new JRadioButtonMenuItem("White player");
        White.setActionCommand("White");
        Black = new JRadioButtonMenuItem("Black player");
        Black.setActionCommand("Black");

        ButtonGroup buttonGroup = new ButtonGroup();
        JMenu choosePlayerMenu = new JMenu("Choose player");
        buttonGroup.add(Both);
        choosePlayerMenu.add(Both);
        buttonGroup.add(White);
        choosePlayerMenu.add(White);
        buttonGroup.add(Black);
        choosePlayerMenu.add(Black);

        this.add(choosePlayerMenu);
    }

    private void initVariantOfGameMenu() {
        JMenu variantOfGameMenu = new JMenu("Game Variants");
        variantButtons = new JRadioButtonMenuItem[variants.getNamesOfVariants().size()];
        for (int i = 0; i < variants.getNamesOfVariants().size(); i++) {
            variantButtons[i] = new JRadioButtonMenuItem(variants.getNamesOfVariants().get(i));
        }
        variantButtons[0].setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < variants.getNamesOfVariants().size(); i++) {
            buttonGroup.add(variantButtons[i]);
            variantOfGameMenu.add(variantButtons[i]);
        }

        this.add(variantOfGameMenu);

    }

    private void initColorMenu() {
        colorButtons = new JRadioButtonMenuItem[3];
        for (int i = 0; i < 3; i++) {
            colorButtons[i] = new JRadioButtonMenuItem();
            colorButtons[i].setActionCommand(String.valueOf(i));
        }
        setColorButtonsIcons();
        colorButtons[0].setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        JMenu colorMenu = new JMenu("Color");
        for (int i = 0; i < 3; i++) {
            buttonGroup.add(colorButtons[i]);
            colorMenu.add(colorButtons[i]);
        }
        this.add(colorMenu);
    }

    public void addNewGameListener(ActionListener actionListener) {
        newGame.addActionListener(actionListener);
    }

    public void addUndoLastMoveListener(ActionListener actionListener) {
        undoLastMove.addActionListener(actionListener);
    }

    public void addSwitchRotationListener(ActionListener actionListener) {
        rotation.addActionListener(actionListener);
    }

    public void addChoosePlayerListener(ActionListener actionListener) {
        Both.addActionListener(actionListener);
        White.addActionListener(actionListener);
        Black.addActionListener(actionListener);
    }

    public void addVariantOfGameListener(ActionListener actionListener) {
        for (int i = 0; i < variants.getNamesOfVariants().size(); i++) {
            variantButtons[i].addActionListener(actionListener);
        }
    }

    public void addColorListener(ActionListener actionListener) {
        for (int i = 0; i < 3; i++) {
            colorButtons[i].addActionListener(actionListener);
        }
    }

    private void setColorButtonsIcons() {
        try {
            BufferedImage buf;
            buf = ImageIO.read(new File("src/main/resources/icons/colorType0.png"));
            colorButtons[0].setIcon(new ImageIcon(buf));
            buf = ImageIO.read(new File("src/main/resources/icons/colorType1.png"));
            colorButtons[1].setIcon(new ImageIcon(buf));
            buf = ImageIO.read(new File("src/main/resources/icons/colorType2.png"));
            colorButtons[2].setIcon(new ImageIcon(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}