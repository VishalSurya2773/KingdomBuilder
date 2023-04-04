import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Game {
    private ArrayList<Player> players;
    private static ArrayList<Card> deck;
    private static ArrayList<Card> discard;
    public static ArrayList<ObjectiveCard> objectives;
    private int playerTurn;

    public Game() {
        players = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
        }
        players.add(new Player(false, null));
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
    }

    public void endGame() {
    }

    public void clearBoard() {
        // traverse through each hex and clear it
    }

    public void reshuffle() {
        Collections.shuffle(deck);
    }

    public void initializeHex() {

    }

    public void chooseStartingPlayer() {
        players.get(0).setFirst();
    }

    public Player[] rankings() {
        Player[] ranks = new Player[4];
        ArrayList<Integer> scores = ArrayList<Integer>();
        for(int i = 0; i<4; i++){
            scores.add(players.get(i).getScore);
        }
        Collections.sort(scores, Collections.reverseOrder());
        return ranks;

    }

    public Player getWinner() {

        return new Player(false, null);
    }
}
