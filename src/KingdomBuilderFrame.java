import java.security.PublicKey;

import javax.swing.*;

public class KingdomBuilderFrame extends JFrame {
    public static int HEIGHT = 900;
    public static int WIDTH = 1550;

    public KingdomBuilderFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new KingdomBuilderPanel());
        setResizable(false);
        setVisible(true);

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}