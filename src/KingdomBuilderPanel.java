import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class KingdomBuilderPanel extends JPanel implements MouseListener, ActionListener {
    private BufferedImage background, b_play, b_guide_start, mainmenu, b_endgame, b_guide, b_home, b_restart, b1, b2,
            b3, b4, b5, b6, b7, b8,
            citizen, discoverer, farmer, fisherman, hermit, knight, lord, merchant, miner, worker, settleBlue,
            settleGreen, settleOrange, settlePurple, settleRed, settleYellow, cardBack, cardCanyon, cardDesert,
            cardFlower, cardForest, cardMeadow, sumBarn, sumFarm, sumHarbor, sumOasis, sumOracle, sumPaddock, sumTavern,
            sumTower, t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower;
    public Player p1, p2, p3, p4;
    private int clickedX, clickedY;
    private ArrayList<Hex> chosenHex; // ??
    private boolean pickHex, startPhase, gamePhase, scoringPhase; // ???
    private JButton playButton, guideButton;
    private JTextField textField;

    public KingdomBuilderPanel() {
        // p1 = new Player(1);
        try {
            // background and buttons
            mainmenu = ImageIO.read(KingdomBuilderPanel.class.getResource("images/main.png"));
            b_play = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_template.png"));
            b_guide_start = ImageIO.read(KingdomBuilderPanel.class.getResource("images/guide_template.png"));
            background = ImageIO.read(KingdomBuilderPanel.class.getResource("images/background.jpg"));
            b_endgame = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_endgame.png"));
            b_guide = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_guide.png"));
            b_home = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_home.png"));
            b_restart = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_restart.png"));
            // boards
            b1 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board1.png"));
            b2 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board2.png"));
            b3 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board3.png"));
            b4 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board4.png"));
            b5 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board5.png"));
            b6 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board6.png"));
            b7 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board7.png"));
            b8 = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Board8.png"));
            // objective cards
            citizen = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Citizens.png"));
            discoverer = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Discoverers.png"));
            farmer = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Farmers.png"));
            fisherman = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Fishermen.png"));
            hermit = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Hermits.png"));
            knight = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Knights.png"));
            lord = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Lords.png"));
            merchant = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Merchants.png"));
            miner = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Miners.png"));
            worker = ImageIO.read(KingdomBuilderPanel.class.getResource("images/Obj_Workers.png"));
            // settlements
            settleBlue = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-blue.png"));
            settleGreen = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-green.png"));
            settleOrange = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-orange.png"));
            settlePurple = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-purple.png"));
            settleRed = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-red.png"));
            settleYellow = ImageIO.read(KingdomBuilderPanel.class.getResource("images/settlement-yellow.png"));
            // terrain cards
            cardBack = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Back.png"));
            cardCanyon = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Canyon.png"));
            cardDesert = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Desert.png"));
            cardFlower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Flower.png"));
            cardForest = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Forest.png"));
            cardMeadow = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Meadow.png"));
            // summary tiles
            sumBarn = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_barn.png"));
            sumFarm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_farm.png"));
            sumHarbor = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_harbor.png"));
            sumOasis = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_oasis.png"));
            sumOracle = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_oracle.png"));
            sumPaddock = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_paddock.png"));
            sumTavern = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_tavern.png"));
            sumTower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_tower.png"));
            // tokens
            t_barn = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_barn.png"));
            t_farm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_farm.png"));
            t_harbor = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_harbor.png"));
            t_oasis = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_oasis.png"));
            t_oracle = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_oracle.png"));
            t_paddock = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_paddock.png"));
            t_tavern = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_tavern.png"));
            t_tower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_tower.png"));

        } catch (Exception e) {
            System.out.println("failure");
        }

        // jbutton stuff for start panel
        Icon play = new ImageIcon(b_play);
        playButton = new JButton(play);
        playButton.addActionListener(this);
        playButton.setBounds(800, 500, 10, 10);
        playButton.setSize(new Dimension(100, 100));

        Icon guide = new ImageIcon(b_guide_start);
        guideButton = new JButton(guide);
        playButton.addActionListener(this);

        this.add(playButton);
        this.add(guideButton);
        playButton.setVisible(false);
        guideButton.setVisible(false);

        startPhase = false;
        gamePhase = true;
        scoringPhase = false;
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        if (startPhase) {
            g.drawImage(mainmenu, 0, 0, 1600, 900, null);
            playButton.setVisible(true);
            guideButton.setVisible(true);
        } else if (gamePhase) {
            g.drawImage(background, 0, 0, 1600, 900, null);
            g.setColor(Color.WHITE);
            Font ps = new Font("Abril Fatface", Font.BOLD, 40);
            g.setFont(ps);
            g.drawString("PLAYER 1", 0, 75);
            g.drawString("PLAYER 2", 1400, 75);
            g.drawString("PLAYER 3", 1400, 525);
            g.drawString("PLAYER 4", 0, 525);
            double thickness = 2;
            Stroke oldStroke = ((Graphics2D) g).getStroke();
            g.setStroke(new BasicStroke(thickness));
            Color burgundy = new Color(128, 0, 32);
            g.setColor(burgundy);
            g.drawRect(0, 100, 300, 300);
            g.drawRect(1350, 100, 300, 300);
            g.drawRect(1350, 540, 300, 300);
            g.drawRect(0, 540, 300, 300);

            g.drawRect(0, 850, 1600, 50);
            g.fillRect(0, 850, 1600, 50);
            g.drawImage(b_home, 50, 850, 50, 50, null);
            g.drawImage(b_guide, 125, 850, 50, 50, null);
            g.drawImage(b_endgame, 200, 850, 50, 50, null);

            // draw board

        }

    }

    public void drawStartScreen(Graphics g) {
    }

    public void drawEndScreen(Graphics g) {
    }

    public void drawBoard(Graphics g) {
    }

    public void drawSpecialCard(Graphics g) {
    }

    public void drawCard(Graphics g) {
    }

    public void drawScore(Graphics g) {
    }

    public void drawSettlement(Graphics g) {
    }

    public void drawSpecialHex(Graphics g) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            System.out.println("game started");
            startPhase = false;
            gamePhase = true;
        }
        if (e.getSource() == guideButton) {
            System.out.println("guide button clicked");
            // open up pdf of guidebook, have the ability to come back to start screen
        }
        repaint();
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
