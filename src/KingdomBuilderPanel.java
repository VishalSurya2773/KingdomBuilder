import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
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
            b3, b4, b5, b6, b7, b8, firstToken,
            citizen, discoverer, farmer, fisherman, hermit, knight, lord, merchant, miner, worker, settleBlue,
            settleGreen, settleOrange, settlePurple, settleRed, settleYellow, cardBack, cardCanyon, cardDesert,
            cardFlower, cardForest, cardMeadow, sumBarn, sumFarm, sumHarbor, sumOasis, sumOracle, sumPaddock, sumTavern,
            sumTower, reverseSumBarn, reverseSumFarm, reverseSumHarbor, reverseSumOasis, reverseSumOracle,
            reverseSumPaddock, reverseSumTavern, reverseSumTower, t_barn, t_farm, t_harbor, t_oasis, t_oracle,
            t_paddock, t_tavern, t_tower;
    public Player p1, p2, p3, p4;
    private int clickedX, clickedY;
    public int numPlayers;
    private ArrayList<Hex> chosenHex; // ??
    private ArrayList<ObjectiveCard> ObjectiveDeck;
    private ArrayList<Player> sortedPlayers;
    private boolean pickHex, startPhase, gamePhase, scoringPhase, playAmtClicked; // ???
    private JButton playButton, guideButton;
    private JTextField textField;
    private Game game;
    private static Board b;
    private static Hex[][] board;
    private int WIDTH, HEIGHT;
    public Graphics graphics;
    public GameStates gameStates = GameStates.startGame;
    public Player currentPlayer;
    private TreeMap<String, BufferedImage> cardMapping;

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
            // reverse summary tiles
            reverseSumBarn = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_barn.png"));
            reverseSumFarm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_farm.png"));
            reverseSumHarbor = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_harbor.png"));
            reverseSumOasis = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_oasis.png"));
            reverseSumOracle = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_oracle.png"));
            reverseSumPaddock = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_paddock.png"));
            reverseSumTavern = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_tavern.png"));
            reverseSumTower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/summary_tower.png"));
            // tokens
            t_barn = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_barn.png"));
            t_farm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_farm.png"));
            t_harbor = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_harbor.png"));
            t_oasis = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_oasis.png"));
            t_oracle = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_oracle.png"));
            t_paddock = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_paddock.png"));
            t_tavern = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_tavern.png"));
            t_tower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/token_tower.png"));
            firstToken = ImageIO.read(KingdomBuilderPanel.class.getResource("images/first_token.png"));

            cardMapping = new TreeMap<String, BufferedImage>();

            loadCardMap();

            GameStates gameStates = GameStates.startGame;

        } catch (Exception e) {
            System.out.println("failure");
        }

        numPlayers = 4;
        WIDTH = KingdomBuilderFrame.WIDTH;
        HEIGHT = KingdomBuilderFrame.HEIGHT;
        sortedPlayers = game.players;
        Collections.sort(sortedPlayers, new sortPlayer());
        startPhase = true;
        gamePhase = false;
        scoringPhase = false;
        addMouseListener(this);
        b = game.gameBoard;
        board = b.getGraph();
    }

    public void loadCardMap() {
        cardMapping.put("canyon", cardCanyon);
        cardMapping.put("forest", cardForest);
        cardMapping.put("meadow", cardMeadow);
        cardMapping.put("desert", cardDesert);
        cardMapping.put("flower", cardFlower);

    }

    // settlements r 36x36
    public void paint(Graphics g) {
        graphics = g;
        Color burgundy = new Color(128, 0, 32);

        switch (gameStates) {
            // System.out.println("this is being reached");
            case startGame:
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
                } else {
                }
                System.out.println("Start Game GameState");
                break;
            case objectiveCards:

                g.drawImage(background, 0, 0, WIDTH, HEIGHT - 1, null);
                g.setColor(Color.WHITE);
                Font ps = new Font("Abril Fatface", Font.BOLD, 76);
                g.setFont(ps);
                g.drawString("Player 1", 0, 80);
                g.drawString("Player 2", 1600, 72);
                g.drawString("Player 3", 1600, 390);
                g.drawString("Player 4", 0, 460);

                g.setColor(burgundy);
                drawBackCards(g);
                drawAmtSettle(g);
                drawDeck(g);
                drawBoard(g);
                drawFirstPlayerToken(g);
                drawPlayerTokens(g);
                drawSumActionTiles(g);

                // g.drawImage(cardBack, 470, 450,110, 180, null);
                // drawHexOutline(g);
                // image.png(g);

                // g.drawRect(0, 128, 340, 340);
                // g.drawRect(1580, 128, 340, 340);
                // g.drawRect(1580, 480, 340, 340);
                // g.drawRect(0, 480, 340, 340);

                // g.drawRect(0, HEIGHT - HEIGHT / 18, WIDTH, HEIGHT / 18);
                // g.fillRect(0, HEIGHT - HEIGHT / 18, WIDTH, HEIGHT / 18);
                // g.drawImage(b_home, WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);
                // g.drawImage(b_guide, 2 * WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);
                // g.drawImage(b_endgame, 3 * WIDTH / 32, HEIGHT - HEIGHT / 18, 50, 50, null);
                drawObjectiveCards(g);
                gameStates = GameStates.drawCard;
                break;

            case drawCard:
                if (currentPlayer.hasDrawn) {
                    if (currentPlayer.playerNum == 1) {
                        BufferedImage b = cardMapping.get(currentPlayer.terrainCard.getTerrain());
                        g.drawImage(b, 350, 140, 175, 270, null);
                        gameStates = GameStates.turnStart;
                    } else if (currentPlayer.playerNum == 2) {
                        BufferedImage b = cardMapping.get(currentPlayer.terrainCard.getTerrain());
                        g.drawImage(b, 1388, 140, 175, 270, null);
                        gameStates = GameStates.turnStart;

                    } else if (currentPlayer.playerNum == 3) {
                        BufferedImage b = cardMapping.get(currentPlayer.terrainCard.getTerrain());
                        g.drawImage(b, 1388, 520, 175, 270, null);
                        gameStates = GameStates.turnStart;

                    } else if (currentPlayer.playerNum == 4) {
                        BufferedImage b = cardMapping.get(currentPlayer.terrainCard.getTerrain());
                        g.drawImage(b, 350, 520, 175, 270, null);
                        gameStates = GameStates.turnStart;

                    }

                }
            case turnStart:

                break;
            case chooseSettlement:

                if (Game.gameOver) {
                    gameStates = GameStates.gameOver;
                    break;
                }

            case gameOver:

        }

    }

    public void drawConfirmButton(Graphics g) {

    }

    public void drawSumActionTiles(Graphics g) {
        // find boards and correlate them w a specialaction tile
        // add each to edge of board -> idk how to rotate them tho
    }

    public void drawDeck(Graphics g) {
        g.drawImage(cardBack, 1715, 800, 200, 270, null);
        g.setColor(Color.WHITE);
        g.drawString("DECK", 1760, 864);
    }

    public void drawBackCards(Graphics g) {
        g.drawImage(cardBack, 350, 140, 175, 270, null);
        g.drawImage(cardBack, 1388, 140, 175, 270, null);
        g.drawImage(cardBack, 1388, 520, 175, 270, null);
        g.drawImage(cardBack, 350, 520, 175, 270, null);

    }

    public void drawFirstPlayerToken(Graphics g) {
        // players = game.players;
        // if(players.size() == 0){System.out.println("PLAYER LIST IS 0"); return;}
        int firstPlayer = 0;
        for (Player i : game.players) {
            if (i.isFirst()) {
                firstPlayer = i.getOrder();
            }
        }
        if (firstPlayer == 1) {
            g.drawImage(firstToken, 405, 31, 100, 85, null);
        } else if (firstPlayer == 2) {
            g.drawImage(firstToken, 1350, 45, 100, 85, null);
        } else if (firstPlayer == 3) {
            g.drawImage(firstToken, 1350, 395, 100, 85, null);
        } else {
            g.drawImage(firstToken, 405, 418, 100, 85, null);
        }
    }

    public void drawAmtSettle(Graphics g) {
        g.setColor(Color.BLACK);
        // g.drawArc(303, 23, 99, 98, 360, 360);
        BufferedImage settlement = null;
        // p1
        settlement = settleImage(sortedPlayers.get(0));
        g.fillArc(303, 20, 99, 98, 360, 360);
        g.drawImage(settlement, 313, 30, 80, 80, null);

        // p2
        settlement = settleImage(sortedPlayers.get(1));
        g.fillArc(1500, 20, 99, 98, 360, 360);
        g.drawImage(settlement, 1510, 30, 80, 80, null);

        // p3
        settlement = settleImage(sortedPlayers.get(2));
        g.fillArc(1500, 420, 99, 98, 360, 360);
        g.drawImage(settlement, 1510, 430, 80, 80, null);

        // p4
        settlement = settleImage(sortedPlayers.get(3));
        g.fillArc(303, 420, 99, 98, 360, 360);
        g.drawImage(settlement, 313, 430, 80, 80, null);

        g.setFont(new Font("Abril Fatface", Font.PLAIN, 40));
        g.setColor(Color.WHITE);
        g.drawString("" + sortedPlayers.get(0).numSettlements(), 315, 95);
        g.drawString("" + sortedPlayers.get(1).numSettlements(), 1512, 95);
        g.drawString("" + sortedPlayers.get(2).numSettlements(), 1512, 495);
        g.drawString("" + sortedPlayers.get(3).numSettlements(), 315, 495);
        g.setColor(Color.BLACK);

    }

    public BufferedImage settleImage(Player p) {
        String clr = p.getColor();
        BufferedImage temp = null;
        BufferedImage[] clrs = { settleBlue, settleGreen, settleOrange, settlePurple, settleRed, settleYellow };
        for (int i = 0; i < clrs.length; i++) {
            if (clr.equals("Blue")) {
                temp = settleBlue;
            } else if (clr.equals("Green")) {
                temp = settleGreen;
            } else if (clr.equals("Orange")) {
                temp = settleOrange;
            } else if (clr.equals("Purple")) {
                temp = settlePurple;
            } else if (clr.equals("Red")) {
                temp = settleRed;
            } else if (clr.equals("Yellow")) {
                temp = settleYellow;
            }
        }
        return temp;
    }

    public void drawStartScreen(Graphics g) {
        g.drawImage(mainmenu, 0, 0, WIDTH, HEIGHT - 1, null);
        // jbutton stuff
    }

    public void drawObjectiveCards(Graphics g) {
        try {
            drawSpecialCard(g);
        } catch (IOException e) {
            System.out.println("draw special cards failed");
        }

    }

    public void drawEndScreen(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    public void drawPlayerTokens(Graphics g) {
        // almost complete
        ArrayList<Player> players = game.players;
        int currX = 0;
        int currY = 150;
        BufferedImage[] actionTiles = { t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower };
        for (int i = 0; i < players.size(); i++) {
            ArrayList<SpecialHex> hand = players.get(i).getHand();
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            if (i == 1 || i == 2) {
                currX = 1390;
            } else if (i == 3) {
                currY = 510;
            }
            for (int j = 0; j < hand.size(); j++) {
                SpecialHex x = hand.get(j);
                BufferedImage temp = null;
                if (x.getType() == "barn") {
                    temp = actionTiles[0];
                } else if (x.getType() == "farm") {
                    temp = actionTiles[1];
                } else if (x.getType() == "harbor") {
                    temp = actionTiles[2];
                } else if (x.getType() == "oasis") {
                    temp = actionTiles[3];
                } else if (x.getType() == "oracle") {
                    temp = actionTiles[4];
                } else if (x.getType() == "paddock") {
                    temp = actionTiles[5];
                } else if (x.getType() == "tavern") {
                    temp = actionTiles[6];
                } else if (x.getType() == "tower") {
                    temp = actionTiles[7];
                }
                g.drawImage(temp, currX, currY, 104, 95, null);
                currX += 110;
                // if (temp != null) {

                // }
                if (j == 3) {
                    currX -= 110;
                    currY += 105;
                }
            }
        }
    }

    public void drawBoard(Graphics g) {
        // find and use variable to store the specific board and then reference the
        // BufferedImage[] imgs = { b1, b2, b3, b4, b5, b6, b7, b8 };
        int[] nums = b.getNumbers();
        int[] currX = { 547, 920, 547, 920 };
        int[] currY = { 130, 130, 477, 477 };
        for (int i = 0; i < 4; i++) { // 620 x 528

            if (nums[i] == 1) {
                g.drawImage(b1, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 2) {
                g.drawImage(b2, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 3) {
                g.drawImage(b3, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 4) {
                g.drawImage(b4, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 5) {
                g.drawImage(b5, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 6) {
                g.drawImage(b6, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 7) {
                g.drawImage(b7, currX[i], currY[i], 426, 363, null);
            } else if (nums[i] == 8) {
                g.drawImage(b8, currX[i], currY[i], 426, 363, null);
            }
        }

    }

    public void drawHexOutline(Graphics g) {
        // Hex1 - center: 664 292
        /*
         * 664, 276
         * 678 284
         * 678 300
         * 665 309
         * 650 300
         * 650 283
         */
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (!board[i][j].getTerrain().equals("")) {
                    int XCoord = board[i][j].getCenterX();
                    int YCoord = board[i][j].getCenterY();
                    g.setColor(Color.BLACK);
                    int[] xPoints = { XCoord, XCoord + 14, XCoord + 14, XCoord, XCoord - 14, XCoord - 14 };
                    int[] yPoints = { YCoord - 16, YCoord - 8, YCoord + 8, YCoord + 18, YCoord + 8, YCoord - 16 };
                    g.setColor(Color.BLACK);
                    g.drawPolygon(xPoints, yPoints, 6);
                }
            }
        }
    }

    public void drawSpecialCard(Graphics g) throws IOException {
        try {
            ObjectiveDeck = game.getObjDeck();
            ObjectiveCard c1 = ObjectiveDeck.get(0);
            ObjectiveCard c2 = ObjectiveDeck.get(1);
            ObjectiveCard c3 = ObjectiveDeck.get(2);
            g.drawImage(c1.getImage(c1.getType()), 675, 835, 160, 225, null); // coordinates are just placeholders rn
            g.drawImage(c2.getImage(c2.getType()), 827, 835, 160, 225, null); // coordinates are just placeholders rn
            g.drawImage(c3.getImage(c3.getType()), 987, 835, 160, 225, null);
        } catch (Exception E) {
            System.out.println("error on special card");
            return;
        }

    }

    public void drawCard(Graphics g) throws IOException {
        BufferedImage cimage = cardBack;
        Card c1 = game.getCard();
        try {
            if (c1.getTerrain().equals("canyon")) {
                cimage = cardCanyon;
            } else if (c1.getTerrain().equals("Desert")) {
                cimage = cardDesert;
            } else if (c1.getTerrain().equals("Meadow")) {
                cimage = cardMeadow;
            } else if (c1.getTerrain().equals("Flower")) {
                cimage = cardFlower;
            } else if (c1.getTerrain().equals("Forest")) {
                cimage = cardForest;
            }
            g.drawImage(cimage, 900, 1200, null);
        } catch (Exception E) {
            System.out.println("error");
        }
        // placeholder coordinates
    }

    public void drawScore(Graphics g) {
        ArrayList<Player> p = game.getPlayers();
        int length = p.size();
        int[] s = new int[length];
        String[] s2 = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = p.get(i).getScore();
            s2[i] = Integer.toString(s[i]);
        }
        for (String x : s2) {
            g.drawString(x, 5000, 5000); // placeholder coordinates
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

        switch (gameStates) {
            case startGame:
                if (clickedX > 850 && clickedX < 1100 && clickedY > 670 && clickedY < 850) {
                    startPhase = false;
                    gamePhase = true;
                    try {
                        game = new Game(numPlayers);
                        gameStates = GameStates.objectiveCards;
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
                break;

            // case objectiveCards:
            // // card back - 470, 450,110, 180
            // if(clickedX >= 470 && clickedX <= 580 && clickedY >= 450 && clickedY <= 630){
            // ObjectiveDeck = game.getObjDeck();
            // Collections.shuffle(ObjectiveDeck);
            // gameStates = gameStates.turnStart;
            // }
            // break;
            case drawCard:
                // 1715, 800, 200, 270
                Player current = getList().get(Game.index % 4 + 1);
                if (clickedX >= 1715 && clickedX <= 1915 && clickedY >= 800 && clickedY <= 1070) {
                    current.drawCard();
                    String terrainType = current.terrainCard.getTerrain();
                    current.hasDrawn = true;
                    System.out.println("has drawn card");
                }
                break;
            case turnStart:

                break;
            case chooseSettlement:
                break;
            case gameOver:
                break;

        }
        if (startPhase) {
            // play button

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

    public ArrayList<Player> getList() {
        ArrayList<Player> players = new ArrayList<>();
        players = Game.players;
        Collections.sort(players, new sortPlayer());
        return players;
    }

}

class sortPlayer implements Comparator<Player> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Player a, Player b) {
        return a.getOrder() - b.getOrder();
    }
}
