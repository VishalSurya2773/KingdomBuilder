import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Game {
    private ArrayList<Player> players;
    private static ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private ArrayList<ObjectiveCard> objectives;
    private int playerTurn;

    public Game() {
        players = new ArrayList<Player>();
        players.add(new Player(false));
        players.add(new Player(false));
        players.add(new Player(false));
        players.add(new Player(false));

        deck = new ArrayList<Card>();
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

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static Card getCard() { // deck has to reset when it's empty - account for it later
        Card ans = deck.get(0);
        deck.remove(0);
        return ans;
    }

    public void startGame() {
    }

    public void endGame() {
    }

    public void clearBoard() {
    }

    public void reshuffle() {
        Collections.shuffle(deck);
    }

    public void initializeHex() {
    }

    public void chooseStartingPlayer() {
        int rand = (int) (Math.random() * 4);
        if (rand == 1) {
            players.get(0).setFirst();
        } else if (rand == 2) {
            players.get(1).setFirst();
        } else if (rand == 3) {
            players.get(2).setFirst();
        } else {
            players.get(3).setFirst();
        }
    }
}
