import java.util.ArrayList;

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

    public void startGame() {
    }

    public void endGame() {
    }

    public void clearBoard() {
    }

    public void reshuffle() {
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
