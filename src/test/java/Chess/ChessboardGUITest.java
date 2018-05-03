package Chess;

import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessboardGUITest extends JFrame implements ActionListener {
    public ChessboardGUITest() {

        initUI();
    }

    private void initUI() {
        ChessboardGUI chess = new ChessboardGUI(StateOfGame.chessboard.getXWidth(), StateOfGame.chessboard.getYWidth(), this, 1, 1);
        createLayout(chess);
        JPanel chesscontainer=new JPanel();
        chesscontainer.add(chess);
        createLayout(chesscontainer);
        this.add(chesscontainer);
        setTitle("Chess.Model.Chessboard");
        setSize(800, 800);
        StateOfGame.chessboard.moveFigure(new Move(new Position(0, 1), new Position(0, 3)));
        chess.updateIcon(new Position(0, 3));
        chess.updateIcon(new Position(0, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ChessboardGUITest ex = new ChessboardGUITest();
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
    }
}
