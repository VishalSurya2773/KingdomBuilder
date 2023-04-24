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
    public int numPlayers;
    private ArrayList<Hex> chosenHex; // ??
    private boolean pickHex, startPhase, gamePhase, scoringPhase, playAmtClicked; // ???
    private JButton playButton, guideButton;
    private JTextField textField;
    private Game game;
    private static Board b;
    private static Hex[][] board;
    private int WIDTH, HEIGHT;
    public Graphics graphics;

    public KingdomBuilderPanel() {
        try {
            // background and buttons
            mainmenu = ImageIO.read(KingdomBuilderPanel.class.getResource("images/main_menu.png"));
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

        WIDTH = KingdomBuilderFrame.WIDTH;
        HEIGHT = KingdomBuilderFrame.HEIGHT;

        System.out.println("w: " + WIDTH + "; h: " + HEIGHT);

        // // jbutton stuff for start panel
        // Icon play = new ImageIcon(b_play);
        // playButton = new JButton(play);
        // playButton.addActionListener(this);
        // playButton.setBounds(800, 500, 10, 10);
        // playButton.setSize(new Dimension(100, 100));

        // Icon guide = new ImageIcon(b_guide_start);
        // guideButton = new JButton(guide);
        // playButton.addActionListener(this);

        // this.add(playButton);
        // this.add(guideButton);
        // playButton.setVisible(false);
        // guideButton.setVisible(false);

        startPhase = true;
        gamePhase = false;
        scoringPhase = false;
        addMouseListener(this);
        b = Game.gameBoard;
        board = Board.getGraph();
    }
    public void paint(Graphics g) {
        graphics = g;
        Color burgundy = new Color(128, 0, 32);
        if (startPhase) {
            drawStartScreen(g);
            if (playAmtClicked) {
                int alpha = 127; // 50% transparent
                Color highlight = new Color(255, 0, 0, alpha);
                if (numPlayers == 2) {
                    highLightRect(g, 925, 960, 85, 80, highlight);
                } else if (numPlayers == 3) {
                    highLightRect(g, 1030, 960, 85, 80, highlight);
                } else if (numPlayers == 4) {
                    highLightRect(g, 1140, 960, 85, 80, highlight);
                }
            }
            // jbutton stuff
        } else if (gamePhase) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT - 1, null);
            g.setColor(Color.WHITE);
            Font ps = new Font("Abril Fatface", Font.BOLD, 40);
            g.setFont(ps);
            g.drawString("PLAYER 1", 0, HEIGHT / 15);
            g.drawString("PLAYER 2", WIDTH - WIDTH / 7, HEIGHT / 15);
            g.drawString("PLAYER 3", WIDTH - WIDTH / 7, HEIGHT / 2 - HEIGHT / 15);
            g.drawString("PLAYER 4", 0, HEIGHT / 2 - HEIGHT / 15);
            g.setColor(burgundy);

            drawBoard(g);
            // drawHexOutline(g);
            // image.png(g);

            g.drawRect(0, 128, 340, 340);
            g.drawRect(1580, 128, 340, 340);
            g.drawRect(1580, 480, 340, 340);
            g.drawRect(0, 480, 340, 340);
            
            // Commented this out bc it blocks the last row of hexes on the game board

            // g.drawRect(0, HEIGHT - HEIGHT / 18, WIDTH, HEIGHT / 18);
            // g.fillRect(0, HEIGHT - HEIGHT / 18, WIDTH, HEIGHT / 18);
            // g.drawImage(b_home, WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);
            // g.drawImage(b_guide, 2 * WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);
            // g.drawImage(b_endgame, 3 * WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);

        }

    }

    public void drawStartScreen(Graphics g) {
        g.drawImage(mainmenu, 0, 0, WIDTH, HEIGHT - 1, null);
        // jbutton stuff
    }

    public void drawEndScreen(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    public void drawBoard(Graphics g) {
        // find and use variable to store the specific board and then reference the
        // BufferedImage[] imgs = { b1, b2, b3, b4, b5, b6, b7, b8 };
        int[] nums = Board.getNumbers();
        int currX = 450;
        int currY = 150;
        for (int i = 0; i < 4; i++) { // 620 x 528

            if (nums[i] == 1) {
                g.drawImage(b1, currX, currY, null);
                currX += 360;
            } else if (nums[i] == 2) {
                g.drawImage(b2, currX, currY, null);
                currX += 360;
            } else if (nums[i] == 3) {
                g.drawImage(b3, currX, currY, null);
                currX += 360;
            } else {
                g.drawImage(b7, currX, currY, null);
                currX += 360;
            }
            if (i == 1) {
                currY += 360;
                currX = 450;
            }
        }
        
    }
    public void drawHexOutline(Graphics g){
        for(int i = 0; i < 40; i++){
            for(int j = 0; j < 40; j++){
                if(board[i][j] != null){
                    int XCoord = board[i][j].getCenterX();
                    int YCoord = board[i][j].getCenterY();
                    g.setColor(Color.BLACK);
                    int[] xPoints = {XCoord-30, XCoord, XCoord+28, XCoord+28, XCoord, XCoord-30};
                    int[] yPoints = {YCoord-17, YCoord-18, YCoord-17, YCoord+17, YCoord+35, YCoord+17};
                    g.drawPolygon(xPoints, yPoints, 6);
                }
            }
        }
    }
    public void drawSpecialCard(Graphics g) throws IOException {
        ObjectiveCard c1 = game.objectives.get(0);
        ObjectiveCard c2 = game.objectives.get(1);
        ObjectiveCard c3 = game.objectives.get(2);
        g.drawImage(c1.getImage(c1.getType()),840,300,null); //coordinates are just placeholders rn
        g.drawImage(c2.getImage(c2.getType()),840,300,null); ///coordinates are just placeholders rn
        g.drawImage(c3.getImage(c3.getType()),840,300,null);
    }

    public void drawCard(Graphics g) throws IOException {
        BufferedImage cimage = cardBack;
        Card c1 = game.getCard();
        boolean b = false;
        while(b==false) {
            if(c1.getTerrain().equals("canyon")) {
                cimage = cardCanyon;
                b = true;
            }else if(c1.getTerrain().equals("Desert")) {
                cimage = cardDesert;
                b = true;
            }else if(c1.getTerrain().equals("Meadow")) {
                cimage = cardMeadow;
                b= true;
            }else if(c1.getTerrain().equals("Flower")) {
                cimage = cardFlower;
                b = true;
            }else if(c1.getTerrain().equals("Forest")) {
                cimage = cardForest;
                b = true;
            }
                
        }
        g.drawImage(cimage ,900, 1200 , null); //placeholder coordinates
    }

    public void drawScore(Graphics g) {
       ArrayList<Player> p = game.getPlayers();
       int length = p.size();
       int[] s = new int[length];
       String[] s2 = new String[length];
       for(int i = 0; i<length; i++) {
        s[i] = p.get(i).getScore();
        s2[i] = Integer.toString(s[i]);
       }
       for(String x : s2) {
        g.drawString(x, 5000, 5000); //placeholder coordinates
       }
    }

    public void drawSettlement(Graphics g) {
    }

    public void drawSpecialHex(Graphics g) {
    }

    public void highLightRect(Graphics g, int x, int y, int w, int h, Color c) {
        g.setColor(c);
        g.drawRect(x, y, w, h);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("x:" + clickedX + ", y:" + clickedY);
        // if (e.getSource() == playButton) {
        // System.out.println("game started");
        // startPhase = false;
        // gamePhase = true;
        // }
        // if (e.getSource() == guideButton) {
        // System.out.println("guide button clicked");
        // // open up pdf of guidebook, have the ability to come back to start screen
        // }
        if (startPhase) {
            // check if play button or guide button are selected
            // take in coordinates for the num of players selection
        }
        repaint();
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
        clickedX = e.getX();
        clickedY = e.getY();
        System.out.println(clickedX + ", " + clickedY);
        // scale all of these w width and height icons
        if (startPhase) {
            // play button
            if (clickedX > 850 && clickedX < 1100 && clickedY > 670
                    && clickedY < 850) {
                startPhase = false;
                gamePhase = true;
                try {
                    game = new Game(numPlayers);
                } catch (IOException a) {
                    System.out.println("Game creation failure");
                }
            } else if (clickedX > 925 && clickedX < 1010 && clickedY > 960 && clickedY < 1040) { // 2 player select
                playAmtClicked = true;
                numPlayers = 2;

                System.out.println("2p");

            } else if (clickedX > 1030 && clickedX < 1120 && clickedY > 960 && clickedY < 1040) { // 3 player select
                playAmtClicked = true;
                numPlayers = 3;
                System.out.println("3p");
            } else if (clickedX > 1140 && clickedX < 1230 && clickedY > 960 && clickedY < 1040) { // 4 player select
                playAmtClicked = true;
                numPlayers = 4;
                System.out.println("4p");
            }
        }

        // else if(clickedX)
        repaint();
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
