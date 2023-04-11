import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Game {
    public static boolean gameOver = false;
    public static ArrayList<Player> players;
    private static ArrayList<Card> deck; // should be displayed
    private static ArrayList<Card> discard; // should be displayed
    public static ArrayList<ObjectiveCard> objectives;
    int amtOfSettlements;
    public Board gameBoard;
    private int playerTurn;

    public Game() { // remember to show the discard pile
        gameBoard = new Board();
        amtOfSettlements = 40; // show this integer on the front end and also make sure to check when it gets
                               // to 0
        players = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
        }
        players.add(new Player(false, null, 0));
        Collections.shuffle(players);

        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();
        // add cards into deck:
        for (int i = 0; i < 5; i++) {
            deck.add(new Card("grass"));
            deck.add(new Card("flower"));
            deck.add(new Card("forest"));
            deck.add(new Card("canyon"));
            deck.add(new Card("desert"));
        }
        Collections.shuffle(deck);
        Collections.shuffle(deck);

        objectives = new ArrayList<ObjectiveCard>();
        String[] objs = { "citizen", "discoverer", "farmer", "fisherman", "hermit", "knight", "lord", "merchant",
                "miner", "worker" };
        for (int i = 0; i < 3; i++) {
            int r = (int) (Math.random() * 9);
            objectives.add(new ObjectiveCard(objs[r]));
        }

    }

    public void addDiscardPile(Card c) {
        discard.add(c);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static Card getCard() { // deck has to reset when it's empty - account for it later
        if (deck.size() == 0) {
            deck = discard;
            Collections.shuffle(deck);
        }
        Card ans = deck.get(0);
        discard.add(deck.remove(0));
        return ans;
    }

    public void startGame() {
        // connect 4 boards - not doing that yet
        reshuffle(); // dont need to add anything
    }

    public void turn(Player p) {
        if (gameOver) {
            endGame();
            return;
        }

    }

    public void useSpecialHex(Player p) {
        if (p.chooseHex().getType().equals("barn")) {
            p.barnAction();
        }

        if (p.chooseHex().getType().equals("farm")) {
            Hex[][] map = Board.Graph;
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map[0].length; c++) {
                    if (map[r][c].getTerrain().equals("grass") && map[r][c].isEmpty()) {
                        // p.farmA
                    }
                }
            }
        }

    }

    public void endGame() {
    }

    public void clearBoard() {
        // traverse through each hex and clear it

    }

    public void reshuffle() {
        Collections.shuffle(deck);
        Collections.shuffle(objectives);
    }

    public void initializeHex() {

    }

    public void chooseStartingPlayer() {
        players.get(0).setFirst();
    }

    public Set<Integer> rankings() {
        // this prolly doesnt work
        Map<Integer, Integer> rankings = new HashMap<Integer, Integer>();
        for (int i = 0; i < 4; i++) {
            rankings.put(i, players.get(i).getScore());
        }
        Map<Integer, Integer> sorted = new TreeMap<>(Comparator.reverseOrder());
        sorted.putAll(rankings);
        Set<Integer> ranks = sorted.keySet();
        return ranks;

    }

    public Player getWinner() {

        return new Player(false, null, 0);
    }
}
