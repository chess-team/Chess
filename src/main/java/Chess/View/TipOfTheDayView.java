package Chess.View;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

import javax.swing.*;
import java.io.FileReader;
import java.io.InputStream;
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
            File file = new File("src/main/resources/tips/" + num + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
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
