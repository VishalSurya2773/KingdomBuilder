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

public class KingdomBuilderPanel extends JPanel implements MouseListener, ActionListener {
    private BufferedImage background, b_play, b_guide_start, mainmenu, b_endgame, b_guide, b_home, b_restart, b1, b2,
            b3, b4, b5, b6, b7, b8, firstToken,
            citizen, discoverer, farmer, fisherman, hermit, knight, lord, merchant, miner, worker, settleBlue,
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
                System.out.println("Start Game GameState");
                break;
            case objectiveCards:
                System.out.println("objectiveCards GameState");
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

                drawObjectiveCards(g);
                gameStates = GameStates.showCard;
                break;
            case showCard: // not needed in the paint class but needed in mouselistener
                System.out.println("showCard GameState");
            case turnStart:
                System.out.println("turnStart GameState");
                Player currTurn = game.getPlayers().get(game.getTurn()); // 1 indexed
                // drawCard(g, currTurn);
                drawPlayerCard(g, currentPlayer.terrainCard.getTerrain(),
                        currentPlayer.getOrder());
                // ************ two cases: starts with specialHex actions, starts with choosing
                // tile ************
                drawPossibleHexOutline(g, currTurn);
                // game.nextTurn();
                // gameStates = GameStates.showCard; // next turn
                currentPlayer = players.get(game.nextPlayer(currentPlayer.getOrder()));
                break;
            case chooseSettlement:
                System.out.println("chooseSettlement GameState");
        }

    }

    public void drawStartScreen(Graphics g) {
        g.drawImage(mainmenu, 0, 0, WIDTH, HEIGHT - 1, null);
        // jbutton stuff
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

    public void drawConfirmButton(Graphics g) {

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

    public void drawPossibleHexOutline(Graphics g, Player p) {
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
        System.out.println("POSSIBLE CHOICES " + possibleChoices.size());

        for (int i = 0; i < possibleChoices.size(); i++) {
            int XCoord = possibleChoices.get(i).getCenterX();
            int YCoord = possibleChoices.get(i).getCenterY();
            // System.out.println("RAN");
            // System.out.println("TERRAIN: " + possibleChoices.get(i).getTerrain() + " "+
            // XCoord + " " + YCoord);
            int[] xPoints = new int[] { XCoord - 20, XCoord, XCoord + 20, XCoord + 20,
                    XCoord, XCoord - 20 };
            int[] yPoints = new int[] { YCoord - 10, YCoord - 22, YCoord - 10, YCoord +
                    10, YCoord + 22, YCoord + 10 };
            g.setColor(Color.RED);
            g.drawPolygon(xPoints, yPoints, 6);
        }
    }

    public void drawCard(Graphics g, Player p) {
        BufferedImage cimage = cardBack;
        Card c1 = game.getCard();
        try {
            if (c1.getTerrain().equals("canyon")) {
                cimage = cardCanyon;
            } else if (c1.getTerrain().equals("desert")) {
                cimage = cardDesert;
            } else if (c1.getTerrain().equals("meadow")) {
                cimage = cardMeadow;
            } else if (c1.getTerrain().equals("flower")) {
                cimage = cardFlower;
            } else if (c1.getTerrain().equals("forest")) {
                cimage = cardForest;
            }
            if (game.getTurn() == 0)
                g.drawImage(cimage, 350, 140, 175, 270, null);
            if (game.getTurn() == 1)
                g.drawImage(cimage, 1388, 140, 175, 270, null);
            if (game.getTurn() == 2)
                g.drawImage(cimage, 1388, 520, 175, 270, null);
            if (game.getTurn() == 3)
                g.drawImage(cimage, 350, 520, 175, 270, null);
            System.out.println(game.getTurn());
        } catch (Exception E) {
            System.out.println("error");
        }
        // placeholder coordinates
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
        BufferedImage setimg = new BufferedImage(getColorModel(), null, gamePhase, null);
        try {
            if (p.getColor().equals("blue")) {
                setimg = settleBlue;
            } else if (p.getColor().equals("red")) {
                setimg = settleRed;
            } else if (p.getColor().equals("yellow")) {
                setimg = settleYellow;
            } else if (p.getColor().equals("green")) {
                setimg = settleGreen;
            } else if (p.getColor().equals("orange")) {
                setimg = settleOrange;
            } else {
                setimg = settlePurple;
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        g.drawImage(setimg, cx, cy, null);
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
                        b = game.gameBoard;
                        board = b.getGraph();
                        players = game.players;
                        currentPlayer = game.players.get(0);
                        sortedPlayers = game.players;
                        Collections.sort(sortedPlayers, new sortPlayer());
                        gameStates = GameStates.objectiveCards;
                        System.out.println("curr player " + currentPlayer.getOrder());
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
                    // draw card for that player
                    currentPlayer.drawCard(game);
                    System.out.println("current card:" + currentPlayer.getOrder() + " "
                            + currentPlayer.getTerrainCard().getTerrain());
                    System.out.println("has drawn card");
                    gameStates = GameStates.turnStart;
                }
                break;
            case turnStart:

                // get coords of available hexes
            case chooseSettlement:
                drawSettlement(currentPlayer, graphics, clickedX, clickedY);
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

}

class sortPlayer implements Comparator<Player> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Player a, Player b) {
        return a.getOrder() - b.getOrder();
    }
}