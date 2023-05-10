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
            cardFlower, cardForest, cardGrass, sumBarn, sumFarm, sumHarbor, sumOasis, sumOracle, sumPaddock, sumTavern,
            sumTower, revSumBarn, revSumFarm, revSumHarbor, revSumOasis, revSumOracle, revSumPaddock, revSumTavern,
            revSumTower, t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower;
    public Player p1, p2, p3, p4;
    private int clickedX, clickedY;
    public int numPlayers, turn;
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
    private String directions;
    private ArrayList<Hex> possibleChoices;
    private Color burgundy, highlight;

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
            cardGrass = ImageIO.read(KingdomBuilderPanel.class.getResource("images/KB-Card-Grass.png"));
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
            System.out.println("images failure");
        }

        numPlayers = 4;
        WIDTH = KingdomBuilderFrame.WIDTH;
        HEIGHT = KingdomBuilderFrame.HEIGHT;
        possibleChoices = new ArrayList<>();

        startPhase = true;
        gamePhase = false;
        scoringPhase = false;
        addMouseListener(this);
        turn = 1;
    }

    // settlements r 36x36
    public void paint(Graphics g) {
        graphics = g;
        burgundy = new Color(128, 0, 32);

        switch (gameStates) {
            // System.out.println("this is being reached");
            case startGame:
                drawStartScreen(g);
                // if (playAmtClicked) {
                // int alpha = 127; // 50% transparent
                // highlight = new Color(255, 0, 0, alpha);
                // if (numPlayers == 2) {
                // highLightRect(g, 925, 960, 85, 80, highlight);
                // } else if (numPlayers == 3) {
                // highLightRect(g, 1030, 960, 85, 80, highlight);
                // } else if (numPlayers == 4) {
                // highLightRect(g, 1140, 960, 85, 80, highlight);
                // }
                // } else {
                // }
                // System.out.println("Start Game GameState");
                break;
            case objectiveCards:
                // System.out.println("objectiveCards GameState");
                setBoard(g);
                gameStates = GameStates.showCard;
                break;
            case showCard: // not needed in the paint class but needed in mouselistener
                System.out.println("p#: " + game.getTurn() + "drawing card");
                drawBackCards(g);
                g.setColor(new Color(255, 165, 0));
                g.fillRect(700, 20, 500, 100);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
                g.drawString("Draw card and place settlements/use tokens", 700, 50);
                g.drawString("Click check button when finished", 700, 70);
                g.drawString("Draw deck to start new turn", 700, 90);
            case turnStart:
                // highlighting the current player turn
                int alpha = 127; // 50% transparent
                highlight = new Color(255, 255, 255, alpha);
                g.setColor(highlight);

                clearHighlightRect(g, 0, 15, 290, 85);
                clearHighlightRect(g, 1600, 15, 290, 85);
                clearHighlightRect(g, 1600, 440, 290, 85);
                clearHighlightRect(g, 0, 440, 290, 85);
                drawPlayerNames(g);
                if (game.getTurn() == 1) {
                    highLightRect(g, 0, 15, 290, 85, highlight);
                } else if (game.getTurn() == 2) {
                    highLightRect(g, 1600, 15, 290, 85, highlight);
                } else if (game.getTurn() == 3) {
                    highLightRect(g, 1600, 440, 290, 85, highlight);
                } else if (game.getTurn() == 4) {
                    highLightRect(g, 0, 440, 290, 85, highlight);
                }

                drawPlayerTokens(g);
                drawAmtSettle(g);
                // System.out.println("Game turn: " + game.getTurn());
                System.out.println("Game turn: " + game.getTurn());
                // System.out.println("turnStart GameState");
                // g.setColor(COLOR.WHITE);
                if (currentPlayer == null) {
                    System.out.println("NULLL");
                }
                if (currentPlayer.terrainCard == null)
                    System.out.println("NULL TERRAIN CARD");

                drawPlayerCard(g, currentPlayer.terrainCard.getTerrain(), game.getTurn());
                // ************ two cases: starts with specialHex actions, starts with choosing
                // tile ************
                drawPossibleHexOutline(g, currentPlayer);
                drawMetasettlement(g);
                break;
            case chooseSettlement:
                drawSettlement(currentPlayer, g, currentPlayer.getPlaceOn());
                clearDrawnPolygons(g); // clears the polygons after player chooses hex - the shown polygons will no
                                       // longer be applicable bc we have to redo possible polygons
                // System.out.println("chooseSettlement GameState");
                drawPossibleHexOutline(g, currentPlayer);
                break;
            case chooseSettlement2:
                drawSettlement(currentPlayer, g, currentPlayer.getPlaceOn());
                clearDrawnPolygons(g);
                drawPossibleHexOutline(g, currentPlayer);
                break;
            case chooseSettlement3:
                drawSettlement(currentPlayer, g, currentPlayer.getPlaceOn());
                clearDrawnPolygons(g);
                break;
            case gameOver:
                g.setColor(Color.white);
                g.drawImage(background, 0, 0, WIDTH, HEIGHT - 1, null);
                g.setFont(new Font("Abril Fatface", Font.PLAIN, 50));
                g.drawString("GAME OVER", 627, 0);
                drawRanks(g);

        }

    }

    public void drawRanks(Graphics g) {
        g.setColor(Color.gray);
        g.drawRect(142, 216, 1596, 764);
        ArrayList<ArrayList<Integer>> rankings = game.rankings();

        g.setFont(new Font("Abril Fatface", Font.PLAIN, 50));

        // top row
        g.drawString("Rank", 170, 238);
        g.drawString("Score", 477, 238);
        g.drawString("Miner", 749, 238);
        g.drawString("Fisherman", 993, 238);
        g.drawString("Knight", 1261, 238);
        g.drawString("City", 1531, 238);

        // actual ranks

        g.drawString("Player " + players.get(rankings.get(0).get(0)).getPlayerNum(), 180, 378);
        g.drawString("Player " + players.get(rankings.get(1).get(0)).getPlayerNum(), 180, 540);
        g.drawString("Player " + players.get(rankings.get(2).get(0)).getPlayerNum(), 180, 672);
        g.drawString("Player " + players.get(rankings.get(3).get(0)).getPlayerNum(), 180, 799);

        // scores

        g.drawString("Player " + players.get(rankings.get(0).get(1)).getPlayerNum(), 460, 371);
        g.drawString("Player " + players.get(rankings.get(1).get(1)).getPlayerNum(), 460, 540);
        g.drawString("Player " + players.get(rankings.get(2).get(1)).getPlayerNum(), 460, 672);
        g.drawString("Player " + players.get(rankings.get(3).get(1)).getPlayerNum(), 460, 799);

        // obj 1

        int Miner1 = game.getObjectives().get(0).miner(players.get(rankings.get(0).get(0)).getColor(), game);
        int Miner2 = game.getObjectives().get(0).miner(players.get(rankings.get(1).get(0)).getColor(), game);
        int Miner3 = game.getObjectives().get(0).miner(players.get(rankings.get(2).get(0)).getColor(), game);
        int Miner4 = game.getObjectives().get(0).miner(players.get(rankings.get(3).get(0)).getColor(), game);

        g.drawString("" + Miner1, 749, 378);
        g.drawString("" + Miner2, 749, 540);
        g.drawString("" + Miner3, 749, 672);
        g.drawString("" + Miner4, 749, 799);

        // obj 2:

        int Fisherman1 = game.getObjectives().get(1).fisherman(players.get(rankings.get(0).get(0)).getColor(), game);
        int Fisherman2 = game.getObjectives().get(1).fisherman(players.get(rankings.get(1).get(0)).getColor(), game);
        int Fisherman3 = game.getObjectives().get(1).fisherman(players.get(rankings.get(2).get(0)).getColor(), game);
        int Fisherman4 = game.getObjectives().get(1).fisherman(players.get(rankings.get(3).get(0)).getColor(), game);

        g.drawString("" + Fisherman1, 993, 378);
        g.drawString("" + Fisherman2, 993, 540);
        g.drawString("" + Fisherman3, 993, 672);
        g.drawString("" + Fisherman4, 993, 799);

        // obj 3
        int Knight1 = game.getObjectives().get(2).knight(players.get(rankings.get(0).get(0)).getColor(), game);
        int Knight2 = game.getObjectives().get(2).knight(players.get(rankings.get(1).get(0)).getColor(), game);
        int Knight3 = game.getObjectives().get(2).knight(players.get(rankings.get(2).get(0)).getColor(), game);
        int Knight4 = game.getObjectives().get(2).knight(players.get(rankings.get(3).get(0)).getColor(), game);

        g.drawString("" + Knight1, 1261, 378);
        g.drawString("" + Knight2, 1261, 540);
        g.drawString("" + Knight3, 1261, 672);
        g.drawString("" + Knight4, 1261, 799);

        // g.drawString(miner(players.get(rankings.get(0).get(0)).get ))

    }

    // public void nextTurn() {
    // turn++;
    // turn %= 5;
    // if (turn == 0)
    // turn = 1;
    // System.out.println("next turn is " + turn);
    // }

    public void setBoard(Graphics g) {
        g.drawImage(background, 0, 0, WIDTH, HEIGHT - 1, null);
        drawPlayerNames(g);
        g.setColor(burgundy);
        drawBackCards(g);
        drawAmtSettle(g);
        drawDeck(g);
        drawBoard(g);
        drawPolygonOutlineForAll(g);
        drawFirstPlayerToken(g);
        drawPlayerTokens(g);
        try {
            drawSumActionTiles(g);
        } catch (IOException e) {
            System.out.println("sumaction draw failed");
        }

        drawObjectiveCards(g);
        drawConfirmButton(g);
        g.setColor(new Color(255, 165, 0));
        g.fillRect(700, 20, 500, 100);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        g.setColor(new Color(255, 165, 0));
        g.fillRect(700, 20, 500, 100);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Abril Fatface", Font.PLAIN, 20));
        g.drawString("Draw card and place settlements/use tokens", 700, 50);
        g.drawString("Click check button when finished", 700, 70);
        g.drawString("Draw deck to start new turn", 700, 90);
        drawMetasettlement(g);
    }

    public void drawPlayerNames(Graphics g) {
        g.setColor(Color.WHITE);
        Font ps = new Font("Abril Fatface", Font.BOLD, 76);
        g.setFont(ps);
        g.drawString("Player 1", 0, 80);
        g.drawString("Player 2", 1600, 80);
        g.drawString("Player 3", 1600, 500);
        g.drawString("Player 4", 0, 500);
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
        String[] randBoards = b.getRandBoards();
        String board1 = "1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 1 0 2 2 2 0 2 2 11 0 0 1 1 2 2 2 2 2 3 3 1 1 1 1 1 2 2 5 3 3 3 1 6 1 1 1 2 3 3 5 4 1 6 6 11 4 3 3 5 3 3 4 6 6 4 4 3 3 6 14 4 4 6 6 6 4 4 5 6 6 4 4 6 6 6 4 5 6 6 4 4 4";
        String board2 = "0 0 1 5 5 4 4 4 6 6 0 14 1 5 4 4 4 8 6 6 1 1 1 3 3 3 4 1 3 3 1 1 3 3 5 0 0 1 1 3 1 6 6 5 3 3 0 0 1 1 6 6 8 3 5 3 5 0 0 1 6 6 6 4 3 3 5 5 0 0 6 6 4 4 2 5 5 5 0 5 6 2 4 4 5 5 5 5 5 5 4 4 4 5 5 5 5 5 5 5";
        String board3 = "6 6 6 4 4 5 6 4 4 4 6 6 6 14 4 5 6 4 4 4 6 3 3 6 4 4 5 6 6 4 3 3 1 6 4 5 3 7 4 4 3 3 3 1 1 5 3 3 5 5 2 2 1 6 6 5 5 5 0 0 1 1 1 2 6 3 3 3 0 0 1 1 14 0 2 0 3 3 1 1 5 5 5 0 0 0 0 2 1 1 5 5 5 5 0 0 0 0 0 1";
        String board4 = "6 6 4 4 4 5 6 4 4 3 6 3 4 4 5 6 4 4 3 3 6 3 3 4 5 6 6 3 3 3 3 3 4 4 5 6 2 3 0 0 1 3 14 4 5 6 0 0 0 0 1 1 4 5 6 6 2 2 0 0 1 1 5 5 5 6 0 0 0 1 5 5 6 6 5 5 12 1 2 1 5 0 12 6 5 2 5 1 1 1 5 0 0 5 5 5 5 1 1 1";
        String board5 = "4 4 4 4 2 2 6 2 1 1 4 2 4 4 3 6 2 2 2 1 3 3 4 3 3 3 6 6 5 2 0 3 3 3 5 9 6 5 2 2 0 0 0 0 3 5 6 5 1 1 0 1 0 0 0 5 5 1 6 1 0 0 1 0 0 5 3 12 6 1 1 1 9 0 5 3 3 3 6 6 0 1 5 5 5 4 4 3 6 6 0 1 1 5 4 4 4 6 6 6";
        String board6 = "0 0 1 5 5 4 4 6 6 6 0 1 5 3 3 4 4 4 6 6 0 0 5 3 3 4 4 15 3 6 5 5 5 3 6 4 3 3 3 3 5 5 5 5 6 6 6 6 3 3 5 4 4 5 6 6 1 1 0 1 5 4 1 4 5 6 1 1 0 1 5 12 1 3 5 15 0 0 1 5 5 5 1 3 5 5 5 0 0 5 5 5 5 5 5 5 5 5 5 5";
        String board7 = "1 1 1 0 0 5 0 0 0 0 2 2 1 0 0 5 0 0 0 0 2 2 1 2 2 5 0 0 13 3 2 1 2 2 5 2 0 3 3 3 1 1 4 4 5 2 2 1 3 3 1 4 4 5 1 1 1 2 3 3 1 13 4 4 5 3 3 3 3 3 6 6 4 5 6 14 6 3 6 4 6 6 4 4 5 6 6 6 6 4 6 6 4 4 5 6 6 6 4 4";
        String board8 = "3 0 0 2 2 0 0 1 1 1 3 3 0 0 0 2 2 1 1 1 3 3 3 3 3 3 3 2 2 2 5 5 3 12 6 6 4 4 2 2 3 3 5 5 6 6 6 4 4 1 3 1 1 5 6 4 4 1 1 1 0 3 10 1 5 4 4 10 1 6 0 0 1 5 4 4 6 6 6 6 0 0 0 5 4 4 4 6 6 6 0 0 5 5 4 4 4 6 6 6";

        int[] currX = { 543, 952, 543, 952 };
        int[] currY = { 130, 130, 482, 482 };
        for (int i = 0; i < 4; i++) { // 620 x 528

            if (randBoards[i].equals(board1)) {
                g.drawImage(b1, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board2)) {
                g.drawImage(b2, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board3)) {
                g.drawImage(b3, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board4)) {
                g.drawImage(b4, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board5)) {
                g.drawImage(b5, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board6)) {
                g.drawImage(b6, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board7)) {
                g.drawImage(b7, currX[i], currY[i], 430, 363, null);
            } else if (randBoards[i].equals(board8)) {
                g.drawImage(b8, currX[i], currY[i], 430, 363, null);
            }
        }
    }

    public void drawSumActionTiles(Graphics g) throws IOException {
        int[] boards = b.getNumbers();
        System.out.print("boards for usmac ");
        for (int i : boards) {
            System.out.print(i);
        }
        BufferedImage temp = null;
        int[] currX = { 563, 1220, 581, 1240 };
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
        if (game.players == null) {
            System.out.println("game players is null");
        }
        int firstPlayer = 0;
        // for (Player i : game.players) {
        // if (i.isFirst()) {
        // firstPlayer = i.getOrder();
        // }
        // }

        if (game.getTurn() == 1) {
            g.drawImage(firstToken, 405, 31, 100, 85, null);
        } else if (game.getTurn() == 2) {
            g.drawImage(firstToken, 1380, 31, 100, 85, null);
        } else if (game.getTurn() == 3) {
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
            ObjectiveDeck = game.getObjectives();
            ObjectiveCard c1 = ObjectiveDeck.get(0);
            ObjectiveCard c2 = ObjectiveDeck.get(1);
            ObjectiveCard c3 = ObjectiveDeck.get(2);
            g.drawImage(c1.getImage(c1.getType()), 745, 835, 160, 225, null);
            g.drawImage(c2.getImage(c2.getType()), 887, 835, 160, 225, null);
            g.drawImage(c3.getImage(c3.getType()), 1027, 835, 160, 225, null);
        } catch (Exception E) {
            System.out.println("error on special card");
            return;
        }

    }

    public void drawEndScreen(Graphics g) {
        g.drawImage(background, 0, 0, 1915, 1075, null);
        BufferedImage settlement = null;
        Font disp = new Font("Abril Fatface", Font.PLAIN, 34);
        g.setFont(disp);
        drawScore(g);
        // p1
        settlement = settleImage(sortedPlayers.get(0));
        g.drawImage(settlement, 47, 58, 270, 262, null);
        g.drawString("Player 1 Score", 850, 175);
        g.drawString("" + sortedPlayers.get(0).getScore(game), 175, 175);

        // p2
        settlement = settleImage(sortedPlayers.get(1));
        g.drawImage(settlement, 56, 773, 270, 262, null);
        g.drawString("Player 2 Score", 850, 275);
        g.drawString("" + sortedPlayers.get(1).getScore(game), 175, 275);
        // p3
        settlement = settleImage(sortedPlayers.get(2));
        g.drawImage(settlement, 1591, 58, 270, 262, null);
        g.drawString("Player 3 Score", 850, 350);
        g.drawString("" + sortedPlayers.get(2).getScore(game), 175, 350);
        // p4
        settlement = settleImage(sortedPlayers.get(3));
        g.drawImage(settlement, 1591, 430, 270, 262, null);
        g.drawString("Player 4 Score", 850, 425);
        g.drawString("" + sortedPlayers.get(3).getScore(game), 175, 425);
        g.setColor(Color.WHITE);
        g.drawRect(514, 235, 912, 773);
        // rankings
    }

    public void drawPlayerTokens(Graphics g) {
        // almost complete
        if (game.players == null) {
            System.out.println("game players is null");
        }
        ArrayList<Player> players = game.players;
        // players.get(0).addSpecialHexTile(new Hex("barn"));
        // players.get(1).addSpecialHexTile(new Hex("paddock"));
        // players.get(2).addSpecialHexTile(new Hex("oasis"));
        // players.get(3).addSpecialHexTile(new Hex("farm"));
        int currX = 0;
        int currY = 85;
        BufferedImage[] actionTiles = { t_barn, t_farm, t_harbor, t_oasis, t_oracle, t_paddock, t_tavern, t_tower };
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Hex> hand = players.get(i).getHand();
            if (i == 1) {
                currX = 1640;
                currY = 85;
            } else if (i == 2) {
                currX = 1640;
                currY = 510;

            } else if (i == 3) {
                currX = 0;
                currY = 510;
            }
            for (int j = 0; j < hand.size(); j++) {
                Hex x = hand.get(j);
                BufferedImage temp = null;
                if (x.getTerrain() == "barn") {
                    temp = actionTiles[0];
                } else if (x.getTerrain() == "farm") {
                    temp = actionTiles[1];
                } else if (x.getTerrain() == "harbor") {
                    temp = actionTiles[2];
                } else if (x.getTerrain() == "oasis") {
                    temp = actionTiles[3];
                } else if (x.getTerrain() == "oracle") {
                    temp = actionTiles[4];
                } else if (x.getTerrain() == "paddock") {
                    temp = actionTiles[5];
                } else if (x.getTerrain() == "tavern") {
                    temp = actionTiles[6];
                } else if (x.getTerrain() == "tower") {
                    temp = actionTiles[7];
                }
                g.drawImage(temp, currX, currY, 123, 141, null);
                currX += 140;
                // if (temp != null) {

                // }
                if (j == 1) {
                    currX -= 280;
                    currY += 150;
                }
            }
        }
    }

    public void drawPolygonOutlineForAll(Graphics g) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                if (board[i][j].getTerrain().equals("blank"))
                    continue;
                int[] xPoints = board[i][j].getXPoints();
                int[] yPoints = board[i][j].getYPoints();
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 6);
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
        possibleChoices = p.getPossible(b);

        /*
         * (FOR TESTING)
         * for(int i = 0; i < 20; i++){
         * for(int j = 0; j < 40; j++){
         * if(board[i][j].getTerrain().equals("blank")) continue;
         * possibleChoices.add(board[i][j]);
         * }
         * }
         */

        // System.out.println("POSSIBLE CHOICES " + possibleChoices.size());
        for (int i = 0; i < possibleChoices.size(); i++) {
            int[] xPoints = possibleChoices.get(i).getXPoints();
            int[] yPoints = possibleChoices.get(i).getYPoints();
            // System.out.println("RAN");
            // System.out.println("TERRAIN: " + possibleChoices.get(i).getTerrain() + " "+
            // XCoord + " " + YCoord);
            Color brRed = new Color(255, 45, 0);
            g.setColor(brRed);
            g.drawPolygon(xPoints, yPoints, 6);

        }
    }

    public void clearDrawnPolygons(Graphics g) {

        for (int i = 0; i < possibleChoices.size(); i++) {
            int[] xPoints = possibleChoices.get(i).getXPoints();
            int[] yPoints = possibleChoices.get(i).getYPoints();
            // System.out.println("RAN");
            // System.out.println("TERRAIN: " + possibleChoices.get(i).getTerrain() + " "+
            // XCoord + " " + YCoord);
            g.setColor(Color.BLACK);
            g.drawPolygon(xPoints, yPoints, 6);
        }

    }

    public void drawPlayerCard(Graphics g, String terrain, int pNum) {
        drawBackCards(g);
        BufferedImage image = null;
        if (terrain.equals("canyon")) {
            image = cardCanyon;
        } else if (terrain.equals("desert")) {
            image = cardDesert;
        } else if (terrain.equals("grass")) {
            image = cardGrass;
        } else if (terrain.equals("flower")) {
            image = cardFlower;
        } else if (terrain.equals("forest")) {
            image = cardForest;
        }

        if (pNum == 1) {
            g.drawImage(image, 350, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 520, 175, 270, null);
            g.drawImage(cardBack, 350, 520, 175, 270, null);
        } else if (pNum == 2) {
            g.drawImage(cardBack, 350, 140, 175, 270, null);
            g.drawImage(image, 1388, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 520, 175, 270, null);
            g.drawImage(cardBack, 350, 520, 175, 270, null);
        } else if (pNum == 3) {
            g.drawImage(cardBack, 350, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 140, 175, 270, null);
            g.drawImage(image, 1388, 520, 175, 270, null);
            g.drawImage(cardBack, 350, 520, 175, 270, null);
        } else if (pNum == 4) {
            g.drawImage(cardBack, 350, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 140, 175, 270, null);
            g.drawImage(cardBack, 1388, 520, 175, 270, null);
            g.drawImage(image, 350, 520, 175, 270, null);
        }
    }

    public void drawScore(Graphics g) {
        ArrayList<Player> p = game.getPlayers();
        int length = p.size();
        int[] s = new int[length];
        String[] s2 = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = p.get(i).getScore(game);
            s2[i] = Integer.toString(s[i]);
        }
        for (String x : s2) {
            g.drawString(x, 5000, 5000); // placeholder coordinates
        }
    }

    public void drawSettlement(Player p, Graphics g, Hex h) {
        // Hex h = p.findHex(cx, cy, game);
        if (h != null) {
            BufferedImage setimg = null;
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
            g.drawImage(setimg, h.getCenterX() - 15, h.getCenterY() - 15, 30, 30, null);
            // h.setSettlement(p.getSettlementFromStore(game));
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
        int alpha = 127; // 50% transparent
        highlight = new Color(255, 255, 255, alpha);
        g.setColor(highlight);
        g.fillRect(x, y, w, h);
        // g.fillRect(x, y, w, h);
    }

    public void clearHighlightRect(Graphics g, int x, int y, int w, int h) {
        // g.clearRect(x, y, w, h);
        // g.setColor(Color.BLACK);
        g.setColor(new Color(218, 91, 45));
        g.fillRect(x, y, w, h);
        //
        // g.fillRect(x, y, w, h);
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
        clickedX = e.getX();
        clickedY = e.getY();
        System.out.println(clickedX + ", " + clickedY);
        // scale all of these w width and height icons

        switch (gameStates) {
            case startGame:
                if (clickedX > 780 && clickedX < 1200 && clickedY > 690 && clickedY < 970) {
                    startPhase = false;
                    gamePhase = true;
                    try {
                        game = new Game(numPlayers);
                        b = game.gameBoard;
                        board = b.getGraph();
                        players = game.getPlayers();
                        currentPlayer = players.get(game.getTurn() - 1);
                        sortedPlayers = players;
                        Collections.sort(sortedPlayers, new sortPlayer());
                        gameStates = GameStates.objectiveCards;
                    } catch (IOException a) {
                        System.out.println("Game creation failure");
                    }
                }
                // else if (clickedX > 925 && clickedX < 1010 && clickedY > 960 && clickedY <
                // 1040) { // 2 player select
                // playAmtClicked = true;
                // numPlayers = 2;
                // System.out.println("2p");

                // } else if (clickedX > 1030 && clickedX < 1120 && clickedY > 960 && clickedY <
                // 1040) { // 3 player select
                // playAmtClicked = true;
                // numPlayers = 3;
                // System.out.println("3p");
                // } else if (clickedX > 1140 && clickedX < 1230 && clickedY > 960 && clickedY <
                // 1040) { // 4 player select
                // playAmtClicked = true;
                // numPlayers = 4;
                // System.out.println("4p");
                // }
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
                    // if (clickedX > 300 && clickedX < 400 && clickedY > 20 && clickedY < 120) { //
                    // settlement button
                    // System.out.println("p1 use settlement");
                    // currentPlayer.placeSettle = true;
                    // directions = "Choose hex to place settlement in";
                    // } else
                    if (clickedX > 0 && clickedX < 320 && clickedY > 80 && clickedY < 430
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
                        directions = "Choose settlement in hand or board to play action tile";
                    } else { // settlement button
                        System.out.println("p1 use settlement");
                        currentPlayer.placeSettle = true;
                        directions = "Choose hex to place settlement in";
                    }
                } else if (game.getTurn() == 2) {
                    // if (clickedX > 1500 && clickedX < 1600 && clickedY > 20 && clickedY < 120) {
                    // System.out.println("p2 use settlement");
                    // currentPlayer.placeSettle = true;
                    // directions = "Choose hex to place settlement in";
                    // } else
                    if (clickedX > 1620 && clickedX < 1900 && clickedY > 80 && clickedY < 430) {
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
                        directions = "Choose settlement in hand or board to play action tile";
                    } else {
                        System.out.println("p2 use settlement");
                        currentPlayer.placeSettle = true;
                        directions = "Choose hex to place settlement in";
                    }
                } else if (game.getTurn() == 3) {
                    // if (clickedX > 1500 && clickedX < 1600 && clickedY > 420 && clickedY < 520) {
                    // System.out.println("p3 use settlement");
                    // currentPlayer.placeSettle = true;
                    // directions = "Choose hex to place settlement in";
                    // } else
                    if (clickedX > 1620 && clickedX < 1900 && clickedY > 500 && clickedY < 850) {
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
                        directions = "Choose settlement in hand or board to play action tile";
                    } else {
                        System.out.println("p3 use settlement");
                        currentPlayer.placeSettle = true;
                        directions = "Choose hex to place settlement in";
                    }
                } else if (game.getTurn() == 4) {
                    // if (clickedX > 300 && clickedX < 400 && clickedY > 420 && clickedY < 520) {
                    // System.out.println("p4 use settlement");
                    // currentPlayer.placeSettle = true;
                    // directions = "Choose hex to place settlement in";
                    // } else
                    if (clickedX > 5 && clickedX < 315 && clickedY > 500 && clickedY < 850) {
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
                        directions = "Choose settlement in hand or board to play action tile";
                    } else {
                        System.out.println("p4 use settlement");
                        currentPlayer.placeSettle = true;
                        directions = "Choose hex to place settlement in";
                    }
                }
                // break;
                if (currentPlayer.placeSettle) {
                    for (int i = 0; i < possibleChoices.size(); i++) {
                        if (possibleChoices.get(i).isClicked(clickedX, clickedY)) {
                            gameStates = GameStates.chooseSettlement;
                            currentPlayer.setPlaceOn(possibleChoices.get(i));
                            possibleChoices.get(i).setAvail(false);
                            currentPlayer.findTokens(possibleChoices.get(i));
                            System.out.println("CHOOSE SETTLEMENT GAMESTATE");
                        }
                    }
                }
                repaint();
                break;
            case chooseSettlement:
                for (int i = 0; i < possibleChoices.size(); i++) {
                    if (possibleChoices.get(i).isClicked(clickedX, clickedY)) {
                        gameStates = GameStates.chooseSettlement2;
                        currentPlayer.setPlaceOn(possibleChoices.get(i));
                        possibleChoices.get(i).setAvail(false);
                        currentPlayer.findTokens(possibleChoices.get(i));
                        System.out.println("CHOOSE SETTLEMENT2 GAMESTATE");
                    }
                }
                // repaint();
                break;
            case chooseSettlement2:
                for (int i = 0; i < possibleChoices.size(); i++) {
                    if (possibleChoices.get(i).isClicked(clickedX, clickedY)) {
                        gameStates = GameStates.chooseSettlement3;
                        currentPlayer.setPlaceOn(possibleChoices.get(i));
                        possibleChoices.get(i).setAvail(false);
                        currentPlayer.findTokens(possibleChoices.get(i));
                        b.printGraph();
                        System.out.println("CHOOSE SETTLEMENT3 GAMESTATE");
                    }
                }
                // repaint();
                break;
            case chooseSettlement3:
                // implement if the player clicks the confirm button
                if (clickedX > 55 && clickedX < 150 && clickedY > 910 && clickedY < 1014) { // confirmation button
                    System.out.println("conf_b clicked");
                    if (!game.gameOver) {
                        game.nextTurn();
                        currentPlayer = players.get(game.getTurn() - 1);
                        gameStates = GameStates.showCard;
                        // repaint();
                    } else {
                        gameStates = GameStates.gameOver;
                    }
                }
                repaint();
                break;
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