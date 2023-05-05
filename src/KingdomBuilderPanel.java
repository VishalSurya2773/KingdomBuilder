import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class KingdomBuilderPanel extends JPanel implements MouseListener {
    private BufferedImage background, b_play, b_guide_start, mainmenu, b_endgame, b_guide, b_home, b_restart, b1, b2,
            b3, b4, b5, b6, b7, b8, firstToken, b_confirm, citizen, discoverer, farmer, fisherman, hermit, knight, lord,
            merchant, miner, worker, settleBlue,
            settleGreen, settleOrange, settlePurple, settleRed, settleYellow, cardBack, cardCanyon, cardDesert,
            cardFlower, cardForest, cardMeadow, sumBarn, sumFarm, sumHarbor, sumOasis, sumOracle, sumPaddock, sumTavern,
            sumTower, revSumBarn, revSumFarm, revSumHarbor, revSumOasis, revSumOracle, revSumPaddock, revSumTavern,
            revSumTower, t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower;
    public Player p1, p2, p3, p4;
    private int clickedX, clickedY;
    public int numPlayers;
    private ArrayList<Hex> chosenHex; // ??
    private ArrayList<ObjectiveCard> ObjectiveDeck;
    private ArrayList<Player> players, sortedPlayers;
    private boolean pickHex, startPhase, gamePhase, scoringPhase, playAmtClicked; // ???
    private JButton playButton, guideButton;
    private JTextField textField;
    private Game game;
    private static Board b;
    private static Hex[][] board;
    private int WIDTH, HEIGHT;
    public Graphics graphics;
    public GameStates gameStates = GameStates.startGame;
    private Player currentPlayer;

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
            b_confirm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/button_confirm.png"));
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
            // reverse
            revSumBarn = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_barn.png"));
            revSumFarm = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_farm.png"));
            revSumHarbor = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_harbor.png"));
            revSumOasis = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_oasis.png"));
            revSumOracle = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_oracle.png"));
            revSumPaddock = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_paddock.png"));
            revSumTavern = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_tavern.png"));
            revSumTower = ImageIO.read(KingdomBuilderPanel.class.getResource("images/r_summary_tower.png"));

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
            GameStates gameStates = GameStates.startGame;

        } catch (Exception e) {
            System.out.println("failure");
        }

        numPlayers = 4;
        WIDTH = KingdomBuilderFrame.WIDTH;
        HEIGHT = KingdomBuilderFrame.HEIGHT;

        startPhase = true;
        gamePhase = false;
        scoringPhase = false;
        addMouseListener(this);
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
                // System.out.println("Start Game GameState");
                break;
            case objectiveCards:
                // System.out.println("objectiveCards GameState");
                g.drawImage(background, 0, 0, WIDTH, HEIGHT - 1, null);
                g.setColor(Color.WHITE);
                Font ps = new Font("Abril Fatface", Font.BOLD, 76);
                g.setFont(ps);
                g.drawString("Player 1", 0, 80);
                g.drawString("Player 2", 1600, 80);
                g.drawString("Player 3", 1600, 500);
                g.drawString("Player 4", 0, 500);

                g.setColor(burgundy);
                drawBackCards(g);
                drawAmtSettle(g);
                drawDeck(g);
                drawBoard(g);
                drawFirstPlayerToken(g);
                drawPlayerTokens(g);
                drawSumActionTiles(g);
                drawObjectiveCards(g);
                drawConfirmButton(g);
                g.drawString("Draw a card from the deck", 720, 20);
                gameStates = GameStates.showCard;
                break;
            case showCard: // not needed in the paint class but needed in mouselistener
                // System.out.println("showCard GameState");
                g.drawString("Select a settlement or token to play", 720, 20);
            case turnStart:
                // System.out.println("turnStart GameState");
                // if(players.get(game.getTurn() - 1).terrainCard.getTerrain() == null) System.out.println("NULL TERRAIN CARD");
                drawPlayerCard(g, currentPlayer.terrainCard.getTerrain(), game.getTurn());
                // ************ two cases: starts with specialHex actions, starts with choosing
                // tile ************
                drawPossibleHexOutline(g, currentPlayer);
                //drawMetasettlement(g);
                // game.nextTurn();
                // gameStates = GameStates.showCard; // next turn

                break;
            case chooseSettlement:
                // System.out.println("chooseSettlement GameState");
        }

    }

    public void drawStartScreen(Graphics g) {
        g.drawImage(mainmenu, 0, 0, WIDTH, HEIGHT - 1, null);
        // jbutton stuff
    }

    public void drawConfirmButton(Graphics g) {
        g.drawImage(b_confirm, 56, 910, 108, 108, null);
    }

    public void drawBoard(Graphics g) {
        // find and use variable to store the specific board and then reference the
        // BufferedImage[] imgs = { b1, b2, b3, b4, b5, b6, b7, b8 };
        int[] nums = b.getNumbers();
        int[] currX = { 543, 946, 543, 946 };
        int[] currY = { 130, 130, 482, 482 };
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

    public void drawSumActionTiles(Graphics g) {
        // find boards and correlate them w a specialaction tile
        // add each to edge of board -> idk how to rotate them tho
        int[] boards = b.getNumbers();
        BufferedImage temp = null;
        int[] currX = { 563, 1209, 581, 1229 };
        int[] currY = { 25, 25, 830, 830 };
        for (int i = 0; i < boards.length; i++) {
            if (i <= 1) {
                if (boards[i] == 1) {
                    temp = sumBarn;
                } else if (boards[i] == 2) {
                    temp = sumFarm;
                } else if (boards[i] == 3) {
                    temp = sumOracle;
                } else if (boards[i] == 4) {
                    temp = sumHarbor;
                } else if (boards[i] == 5) {
                    temp = sumTower;
                } else if (boards[i] == 6) {
                    temp = sumOasis;
                } else if (boards[i] == 7) {
                    temp = sumPaddock;
                } else if (boards[i] == 8) {
                    temp = sumTavern;
                }
            } else if (i >= 2) {
                if (boards[i] == 1) {
                    temp = revSumBarn;
                } else if (boards[i] == 2) {
                    temp = revSumFarm;
                } else if (boards[i] == 3) {
                    temp = revSumOracle;
                } else if (boards[i] == 4) {
                    temp = revSumHarbor;
                } else if (boards[i] == 5) {
                    temp = revSumTower;
                } else if (boards[i] == 6) {
                    temp = revSumOasis;
                } else if (boards[i] == 7) {
                    temp = revSumPaddock;
                } else if (boards[i] == 8) {
                    temp = revSumTavern;
                }
            }
            g.drawImage(temp, currX[i], currY[i], 125, 118, null);
        }

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
            g.drawImage(firstToken, 1380, 31, 100, 85, null);
        } else if (firstPlayer == 3) {
            g.drawImage(firstToken, 1380, 418, 100, 85, null);
        } else {
            g.drawImage(firstToken, 405, 418, 100, 85, null);
        }
    }

    public void drawAmtSettle(Graphics g) {
        g.setColor(Color.BLACK);
        // g.drawArc(303, 23, 99, 98, 360, 360);
        ArrayList<Player> playClrs = new ArrayList<>();
        playClrs = game.players;
        Collections.sort(playClrs, new sortPlayer());
        BufferedImage settlement = null;
        // p1
        settlement = settleImage(playClrs.get(0));
        g.fillArc(303, 20, 99, 98, 360, 360);
        g.drawImage(settlement, 313, 30, 80, 80, null);

        // p2
        settlement = settleImage(playClrs.get(1));
        g.fillArc(1500, 20, 99, 98, 360, 360);
        g.drawImage(settlement, 1510, 30, 80, 80, null);

        // p3
        settlement = settleImage(playClrs.get(2));
        g.fillArc(1500, 420, 99, 98, 360, 360);
        g.drawImage(settlement, 1510, 430, 80, 80, null);

        // p4
        settlement = settleImage(playClrs.get(3));
        g.fillArc(303, 420, 99, 98, 360, 360);
        g.drawImage(settlement, 313, 430, 80, 80, null);

        g.setFont(new Font("Abril Fatface", Font.PLAIN, 40));
        g.setColor(Color.WHITE);
        g.drawString("" + playClrs.get(0).numSettlements(), 315, 95);
        g.drawString("" + playClrs.get(1).numSettlements(), 1512, 95);
        g.drawString("" + playClrs.get(2).numSettlements(), 1512, 495);
        g.drawString("" + playClrs.get(3).numSettlements(), 315, 495);
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

    public void drawObjectiveCards(Graphics g) {
        try {
            drawSpecialCard(g);
        } catch (IOException e) {
            System.out.println("draw special cards failed");
        }

    }

    public void drawSpecialCard(Graphics g) throws IOException {
        try {
            ObjectiveDeck = game.getObjDeck();
            ObjectiveCard c1 = ObjectiveDeck.get(0);
            ObjectiveCard c2 = ObjectiveDeck.get(1);
            ObjectiveCard c3 = ObjectiveDeck.get(2);
            g.drawImage(c1.getImage(c1.getType()), 745, 835, 160, 225, null); // coordinates are just placeholders rn
            g.drawImage(c2.getImage(c2.getType()), 887, 835, 160, 225, null); // coordinates are just placeholders rn
            g.drawImage(c3.getImage(c3.getType()), 1027, 835, 160, 225, null);
        } catch (Exception E) {
            System.out.println("error on special card");
            return;
        }

    }

    public void drawEndScreen(Graphics g) {
        g.drawImage(background, 0, 0, null);
        BufferedImage settlement = null;
        Font disp = new Font("Abril Fatface", Font.PLAIN, 34);
        g.setFont(disp);
        // p1
        settlement = settleImage(sortedPlayers.get(0));
        g.drawImage(settlement, 47, 58, 270, 262, null);
        g.drawString("Player 1 Score", 75, 175);

        // p2
        settlement = settleImage(sortedPlayers.get(1));
        g.drawImage(settlement, 56, 773, 270, 262, null);
        g.drawString("Player 1 Score", 75, 898);
        // p3
        settlement = settleImage(sortedPlayers.get(2));
        g.drawImage(settlement, 1591, 58, 270, 262, null);
        g.drawString("Player 1 Score", 1608, 180);

        // p4
        settlement = settleImage(sortedPlayers.get(3));
        g.drawImage(settlement, 1591, 430, 773, 80, null);
        g.drawString("Player 1 Score", 1608, 885);

        g.drawRect(514, 235, 912, 773);
        // rankings
    }

    public void drawPlayerTokens(Graphics g) {
        // almost complete
        ArrayList<Player> players = game.players;
        int currX = 0;
        int currY = 85;
        BufferedImage[] actionTiles = { t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower };
        for (int i = 0; i < players.size(); i++) {
            ArrayList<SpecialHex> hand = players.get(i).getHand();
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            hand.add(new SpecialHex("barn"));
            if (i == 1) {
                currX = 1590;
                currY = 85;
            } else if (i == 2) {
                currX = 1590;
                currY = 510;

            } else if (i == 3) {
                currX = 0;
                currY = 510;
            }
            for (int j = 0; j < hand.size(); j++) {
                SpecialHex x = hand.get(j);
                BufferedImage temp = actionTiles[0];
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
                g.drawImage(temp, currX, currY, 155, 170, null);
                currX += 165;
                // if (temp != null) {

                // }
                if (j == 1) {
                    currX -= 330;
                    currY += 175;
                }
            }
        }
    }

    public void drawPossibleHexOutline(Graphics g, Player p) { // placed = 0 for some of them - remember to fix
        // Hex1 - center: 567 149
        /*
         * 664, 276
         * 678 284
         * 678 300
         * 665 309
         * 650 300
         * 650 283
         */
        ArrayList<Hex> possibleChoices = new ArrayList<>();
        possibleChoices = p.getPossible(b);
        // System.out.println("POSSIBLE CHOICES " + possibleChoices.size());
        possibleChoices.add(board[0][0]);
        for (int i = 0; i < possibleChoices.size(); i++) {
            int[] xPoints = possibleChoices.get(i).getXPoints();
            int[] yPoints = possibleChoices.get(i).getYPoints();
            // System.out.println("RAN");
            // System.out.println("TERRAIN: " + possibleChoices.get(i).getTerrain() + " "+
            // XCoord + " " + YCoord);
            
            g.setColor(Color.RED);
            g.drawPolygon(xPoints, yPoints, 6);
        }
    }

    public void drawPlayerCard(Graphics g, String terrain, int pNum) {
        drawBackCards(g);
        BufferedImage image = cardCanyon;
        if (terrain.equals("canyon")) {
            image = cardCanyon;
        } else if (terrain.equals("desert")) {
            image = cardDesert;
        } else if (terrain.equals("meadow")) {
            image = cardMeadow;
        } else if (terrain.equals("flower")) {
            image = cardFlower;
        } else if (terrain.equals("forest")) {
            image = cardForest;
        }

        if (pNum == 1) {
            g.drawImage(image, 350, 140, 175, 270, null);
        } else if (pNum == 2) {
            g.drawImage(image, 1388, 140, 175, 270, null);
        } else if (pNum == 3) {
            g.drawImage(image, 1388, 520, 175, 270, null);
        } else if (pNum == 4) {
            g.drawImage(image, 350, 520, 175, 270, null);
        }
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

    public void drawSettlement(Player p, Graphics g, int cx, int cy) {
        Hex h = p.findHex(cx, cy, game);
        if (h != null) {
            BufferedImage setimg = new BufferedImage(getColorModel(), null, gamePhase, null);
            try {
                if (p.getColor().equals("Blue")) {
                    setimg = settleBlue;
                } else if (p.getColor().equals("Red")) {
                    setimg = settleRed;
                } else if (p.getColor().equals("Yellow")) {
                    setimg = settleYellow;
                } else if (p.getColor().equals("Green")) {
                    setimg = settleGreen;
                } else if (p.getColor().equals("Orange")) {
                    setimg = settleOrange;
                } else {
                    setimg = settlePurple;
                }
            } catch (Exception e) {
                System.out.println("error");
            }
            g.drawImage(setimg, cx, cy, null);
            h.setSettlement(p.getSettlementFromStore(game));
        }
    }

    public void drawMetasettlement(Graphics g) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                try {
                    if (board[i][j].isEmpty() != true) {
                        if (board[i][j].getSettlement().getColor().equals("Red")) {
                            g.drawImage(settleRed, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35, 35,
                                    null);
                        } else if (board[i][j].getSettlement().getColor().equals("Green")) {
                            g.drawImage(settleGreen, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35,
                                    35, null);
                        } else if (board[i][j].getSettlement().getColor().equals("Blue")) {
                            g.drawImage(settleBlue, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35,
                                    35, null);
                        } else if (board[i][j].getSettlement().getColor().equals("Orange")) {
                            g.drawImage(settleOrange, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35,
                                    35, null);
                        } else if (board[i][j].getSettlement().getColor().equals("Yellow")) {
                            g.drawImage(settleYellow, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35,
                                    35, null);
                        } else if (board[i][j].getSettlement().getColor().equals("Purple")) {
                            g.drawImage(settlePurple, board[i][j].getCenterX() - 15, board[i][j].getCenterY() - 17, 35,
                                    35, null);
                        }
                    }

                } catch (Exception E) {
                    System.out.println("Error");
                }
            }
        }
    }

    public void drawSpecialHex(Graphics g) {

    }

    public void highLightRect(Graphics g, int x, int y, int w, int h, Color c) {
        g.setColor(c);
        g.drawRect(x, y, w, h);
        g.fillRect(x, y, w, h);
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
                        b = game.gameBoard;
                        board = b.getGraph();
                        players = game.players;
                        currentPlayer = game.players.get(game.getTurn() - 1);
                        sortedPlayers = game.players;
                        Collections.sort(sortedPlayers, new sortPlayer());
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
            case showCard:
                if (clickedX >= 1715 && clickedX <= 1915 && clickedY >= 800 && clickedY <= 1070) {
                    currentPlayer.drawCard(game);
                    System.out.println(game.getTurn() + " has drawn card: "
                            + currentPlayer.getTerrainCard().getTerrain());
                    gameStates = GameStates.turnStart;
                }
                break;
            case turnStart:
                System.out.println("turnstart gamestate");
                if (game.getTurn() == 1) {
                    if (clickedX > 300 && clickedX < 400 && clickedY > 20 && clickedY < 120) { // settlement button
                        System.out.println("p1 use settlement");
                        currentPlayer.placeSettle = true;
                    } else if (clickedX > 0 && clickedX < 320 && clickedY > 80 && clickedY < 430
                            && currentPlayer.getHand().size() > 0) { // token area
                        System.out.println("p1 uses token");
                        currentPlayer.useToken = true;
                        // check each individual square in the box 320 x 350
                        if (clickedX > 0 && clickedX < 160 && clickedY > 80 && clickedY < 225) { // first
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(0));
                            System.out.println("p1 played first token");
                        } else if (clickedX > 160 && clickedX < 320 && clickedY > 80 && clickedY < 225
                                && currentPlayer.getHand().size() > 1) { // second
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(1));
                            System.out.println("p1 played second token");
                        } else if (clickedX > 0 && clickedX < 160 && clickedY > 225 && clickedY < 430
                                && currentPlayer.getHand().size() > 2) { // third
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(2));
                            System.out.println("p1 played third token");
                        } else if (clickedX > 160 && clickedX < 320 && clickedY > 225 && clickedY < 420
                                && currentPlayer.getHand().size() > 3) { // fourth
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(3));
                            System.out.println("p1 played fourth token");
                        }
                    }
                } else if (game.getTurn() == 2) {
                    if (clickedX > 1500 && clickedX < 1600 && clickedY > 20 && clickedY < 120) {
                        System.out.println("p2 use settlement");
                        currentPlayer.placeSettle = true;
                    } else if (clickedX > 1620 && clickedX < 1900 && clickedY > 80 && clickedY < 430) {
                        System.out.println("p2 uses token");
                        currentPlayer.useToken = true;
                        // check each individual square in the box 310 x350
                        if (clickedX > 1625 && clickedX < 1780 && clickedY > 80 && clickedY < 225) { // first
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(0));
                            System.out.println("p2 played first token");
                        } else if (clickedX > 1780 && clickedX < 1900 && clickedY > 80 && clickedY < 225
                                && currentPlayer.getHand().size() > 1) { // second
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(1));
                            System.out.println("p2 played second token");
                        } else if (clickedX > 1625 && clickedX < 1780 && clickedY > 225 && clickedY < 430
                                && currentPlayer.getHand().size() > 2) { // third
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(2));
                            System.out.println("p2 played third token");
                        } else if (clickedX > 1780 && clickedX < 1900 && clickedY > 225 && clickedY < 420
                                && currentPlayer.getHand().size() > 3) { // fourth
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(3));
                            System.out.println("p2 played fourth token");
                        }
                    }
                } else if (game.getTurn() == 3) {
                    if (clickedX > 1500 && clickedX < 1600 && clickedY > 420 && clickedY < 520) {
                        System.out.println("p3 use settlement");
                        currentPlayer.placeSettle = true;
                    } else if (clickedX > 1620 && clickedX < 1900 && clickedY > 500 && clickedY < 850) {
                        System.out.println("p3 uses token");
                        currentPlayer.useToken = true;
                        // check each individual square in the box 310 x350
                        if (clickedX > 1625 && clickedX < 1780 && clickedY > 500 && clickedY < 675) { // first
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(0));
                            System.out.println("p2 played first token");
                        } else if (clickedX > 1780 && clickedX < 1900 && clickedY > 500 && clickedY < 675
                                && currentPlayer.getHand().size() > 1) { // second
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(1));
                            System.out.println("p2 played second token");
                        } else if (clickedX > 1625 && clickedX < 1780 && clickedY > 675 && clickedY < 850
                                && currentPlayer.getHand().size() > 2) { // third
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(2));
                            System.out.println("p2 played third token");
                        } else if (clickedX > 1900 && clickedX < 1900 && clickedY > 675 && clickedY < 850
                                && currentPlayer.getHand().size() > 3) { // fourth
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(3));
                            System.out.println("p2 played fourth token");
                        }
                    }
                } else if (game.getTurn() == 4) {
                    if (clickedX > 300 && clickedX < 400 && clickedY > 420 && clickedY < 520) {
                        System.out.println("p4 use settlement");
                        currentPlayer.placeSettle = true;
                    } else if (clickedX > 5 && clickedX < 315 && clickedY > 500 && clickedY < 850) {
                        System.out.println("p4 uses token");
                        currentPlayer.useToken = true;
                        // check each individual square in the box 310 x350x
                        if (clickedX > 5 && clickedX < 155 && clickedY > 500 && clickedY < 675) { // first
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(0));
                            System.out.println("p1 played first token");
                        } else if (clickedX > 155 && clickedX < 315 && clickedY > 500 && clickedY < 675
                                && currentPlayer.getHand().size() > 1) { // second
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(1));
                            System.out.println("p1 played second token");
                        } else if (clickedX > 5 && clickedX < 155 && clickedY > 675 && clickedY < 850
                                && currentPlayer.getHand().size() > 2) { // third
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(2));
                            System.out.println("p1 played third token");
                        } else if (clickedX > 155 && clickedX < 315 && clickedY > 675 && clickedY < 850
                                && currentPlayer.getHand().size() > 3) { // fourth
                            currentPlayer.useSpecialHexTile(currentPlayer.getHand().get(3));
                            System.out.println("p1 played fourth token");
                        }
                    }
                }
                if (clickedX > 0 && clickedX < 108 && clickedY > 726 && clickedY < 834) { // confirmation button
                    System.out.println("conf_b clicked");
                    if (!game.gameOver) {
                        currentPlayer = players.get(game.getTurn() - 1);
                    } else {
                        gameStates = gameStates.gameOver;
                    }
                }
            case chooseSettlement:
            case gameOver:
                break;

        }
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

class sortPlayer implements Comparator<Player> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Player a, Player b) {
        return a.getOrder() - b.getOrder();
    }
}