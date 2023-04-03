import java.util.ArrayList;
import java.util.*;
import java.io.*;
public class Game {
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private ArrayList<ObjectiveCard> objectives;
    private int playerTurn;

    public Game() {
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Card getCard(){ // deck has to reset when it's empty - account for it later
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
