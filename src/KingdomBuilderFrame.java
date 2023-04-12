import javax.swing.*;

public class KingdomBuilderFrame extends JFrame {
    private static int HEIGHT = 900;
    private static int WIDTH = 1600;

    public KingdomBuilderFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new KingdomBuilderPanel());
        setResizable(true);
        setVisible(true);

    }
}