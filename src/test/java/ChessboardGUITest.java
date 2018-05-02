import javax.swing.*;
import java.awt.*;

public class ChessboardGUITest extends JFrame{
    public ChessboardGUITest() {

        initUI();
    }

    private void initUI() {

        ChessboardGUI chess= new ChessboardGUI(new GridLayout(10,10));
        chess.setColor(9);
        chess.setIcons(0);
        chess.initChessboardFrame();
        createLayout(chess);
        JPanel chesscontainer=new JPanel();
        chesscontainer.add(chess);
        createLayout(chesscontainer);
        this.add(chesscontainer);
        setTitle("Chessboard");
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
}
