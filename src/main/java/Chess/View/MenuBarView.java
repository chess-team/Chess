package Chess.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBarView extends JMenuBar {

    private JMenu colorMenu;
    private JRadioButtonMenuItem [] colorButtons;
    private JMenu optionsMenu;
    private JMenuItem newGame;

    MenuBarView(){
        super();
        initOptionsMenu();
        initColorMenu();
    }

    void initOptionsMenu(){
        optionsMenu = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        optionsMenu.add(newGame);
        this.add(optionsMenu);
    }



    void initColorMenu(){
        colorButtons = new JRadioButtonMenuItem[3];
        for( int i = 0 ; i < 3; i++ ){
            colorButtons[i] = new JRadioButtonMenuItem(String.valueOf(i));
        }
        colorButtons[0].setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        colorMenu = new JMenu("Color");
        for( int i = 0 ; i < 3; i++ ){
            buttonGroup.add(colorButtons[i]);
            colorMenu.add(colorButtons[i]);
        }
        this.add(colorMenu);
    }

    public void addColorListeners(ActionListener actionListener){
        for( int i = 0 ; i < 3; i++ ){
            colorButtons[i].addActionListener(actionListener);
        }
    }
    public void addNewGameListener(ActionListener actionListener){
        newGame.addActionListener(actionListener);
    }
}
