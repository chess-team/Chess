package Chess.View;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import java.net.URL;
import java.util.Random;
import java.util.stream.Stream;

import static javax.imageio.ImageIO.read;

public class TipOfTheDayView {
    public static void showDialog(JFrame frame, String dialogName) {
        Random rand = new Random();
        int num = rand.nextInt(50) + 1;
        try {
            BufferedImage bi = read(TipOfTheDayView.class.getResourceAsStream("/icons/tip.png"));
            BufferedReader br = new BufferedReader(new InputStreamReader(ChessboardView.class.getResourceAsStream("/tips/" + num + ".txt")));
            StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp);
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(frame,
                    sb, dialogName, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bi));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
