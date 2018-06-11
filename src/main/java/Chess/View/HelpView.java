package Chess.View;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class HelpView {
    public static void showDialog(JFrame frame, String dialogName, String command) {
        String name = "Cheats";
        if( !command.equals("c") ){
            name = command;
        }
        try {
            BufferedImage bi;
            File pic = new File("src/main/resources/icons/tip.png");
            bi = read(pic);
            File file = new File("src/main/resources/rules/"  + name );
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
