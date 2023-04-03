import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.MouseListener;

public class KingdomBuilderPanel extends JPanel implements MouseListener {
    private BufferedImage asdf;
    public Player p1, p2, p3, p4;

    public KingdomBuilderPanel() {
        try {
            // images
        } catch (Exception e) {
            System.out.println("failure");
        }
        addMouseListener(this);
    }

    public void paint(Graphics g) {
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }

}
