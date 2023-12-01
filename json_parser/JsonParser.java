package json_parser;

import javax.swing.*;
import java.awt.*;

public class JsonParser {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Graphics Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Custom drawing
                g.setColor(Color.RED);
                g.fillRect(50, 50, 100, 100);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
