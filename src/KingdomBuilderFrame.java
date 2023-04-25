import java.security.PublicKey;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class KingdomBuilderFrame extends JFrame {
    public static int WIDTH, HEIGHT;

    public KingdomBuilderFrame(String title) {
        super(title);

        WIDTH = 1920;
        HEIGHT = 1080;
        // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // WIDTH = (int) (screenSize.getWidth());
        // HEIGHT = (int) (screenSize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new KingdomBuilderPanel());
        setResizable(false);
        setVisible(true);

    }
}