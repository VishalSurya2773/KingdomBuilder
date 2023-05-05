import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Game {
    public boolean gameOver = false;
    public ArrayList<Player> players;
    private ArrayList<Card> deck; // should be displayed
    private ArrayList<Card> discard; // should be displayed
    public ArrayList<ObjectiveCard> objDeck;
    public ArrayList<ObjectiveCard> objectives;
    private int playerIndex; // used when doing the turns (should be randomized on the first turn)
    int amtOfSettlements;
    public static Board gameBoard;
    public int turn = 1;

    public Game(int playerAmount) throws IOException { // remember to show the discard pile
        objDeck = new ArrayList<>();
        gameBoard = new Board();
        try {
            gameBoard.makeGraph();
        } catch (IOException a) {
            System.out.println("Board graph building failure");
        }

        amtOfSettlements = 40; // show this int on front end and make sure to check when it gets to 0
        players = new ArrayList<Player>();
        ArrayList<String> clrs = new ArrayList<>();
        clrs.add("Blue");
        clrs.add("Green");
        clrs.add("Orange");
        clrs.add("Purple");
        clrs.add("Red");
        clrs.add("Yellow");
        for (int i = 1; i <= playerAmount; i++) {
            int temp = (int) (Math.random() * clrs.size()) + 1;
            players.add(new Player(false, clrs.get(temp - 1), i));
            clrs.remove(clrs.get(temp - 1));
        }
        Collections.shuffle(players);
        for (Player p : players) {
            System.out.print(p.getOrder() + ", " + p.getColor() + "; ");
        }
        System.out.println();
        chooseStartingPlayer();

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
        System.out.println("DECK SIZE: " + deck.size());
        Collections.shuffle(deck);
        Collections.shuffle(objDeck);
        objectives = new ArrayList<ObjectiveCard>();
        // gameBoard.printGraph();
        fillObjectiveDeck();
        setObjectives(); // fills objective arraylist and draws 3 random objective cards (Make sure to
                         // display them later)
        // startGame();
        initializeTurn();
    }

    public void initializeTurn() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getOrder() == 1)
                turn = i;
        }
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        turn++;
        turn %= 5;
        if (turn == 0)
            turn = 1;
    }

    public ArrayList<ObjectiveCard> getObjDeck() {
        return objDeck;
    }

    public void setObjectives() {
        objectives.add(new ObjectiveCard("miner"));
        objectives.add(new ObjectiveCard("fisherman"));
        objectives.add(new ObjectiveCard("knight"));
        // for (int i = 0; i < 3; i++) {
        // objectives.add(objDeck.get(i));
        // }
    }

    public ArrayList<ObjectiveCard> getObjectives() {
        return objectives;
    }

    public void fillObjectiveDeck() {
        objDeck.add(new ObjectiveCard("citizen"));
        objDeck.add(new ObjectiveCard("discoverer"));
        objDeck.add(new ObjectiveCard("farmer"));
        objDeck.add(new ObjectiveCard("fisherman"));
        objDeck.add(new ObjectiveCard("hermit"));
        objDeck.add(new ObjectiveCard("knight"));
        objDeck.add(new ObjectiveCard("lord"));
        objDeck.add(new ObjectiveCard("merchant"));
        objDeck.add(new ObjectiveCard("miner"));
        objDeck.add(new ObjectiveCard("worker"));
        Collections.shuffle(objDeck);
    }

    public void addDiscardPile(Card c) {
        discard.add(c);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Card getCard() { // deck has to reset when it's empty - account for it later
        if (deck.size() == 0) {
            deck = discard;
            Collections.shuffle(deck);
        }
        Card ans = deck.get(0);
        discard.add(deck.remove(0));
        return ans;
    }

    /*
     * public void turn() { // unfinished - oversees the turns of the players {
     * // probably wont use this method
     * boolean b = this.gameOver;
     * while (b != gameOver) {
     * playerIndex %= 4;
     * Player playing = players.get(playerIndex + 1);
     * playerIndex++;
     * }
     * 
     * }
     */
    public void useSpecialHex(Player p) { // already in player class - don't need to use unless smth comes up
        if (p.chooseHex().getType().equals("barn")) {
            p.barnAction(this);
        }

        if (p.chooseHex().getType().equals("farm")) {
            Hex[][] map = gameBoard.getGraph();
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map[0].length; c++) {
                    if (map[r][c].getTerrain().equals("grass") && map[r][c].isEmpty()) {
                        // p.farmA
                    }
                }
            }
        }

    }

    public boolean gameOver() {
        return gameOver;
    }

    public void endGame() {
        // scores everything
        for (int i = 0; i < players.size(); i++) {
            players.get(i).calculateScore(this);
        }
        ArrayList<ArrayList<Integer>> playerRankings = rankings();
        ArrayList<Integer> Winners = getWinner();
        gameOver = true; // it's an arraylist because of possible ties
        // show winners and ranks
        // if they want to play again, maybe have a play again button that starts the
        // game over (if we have extra time)

    }

    public void chooseStartingPlayer() {
        players.get(0).setFirst();
    }

    // public int nextPlayer(int p) {
    // if (p < 4) {
    // return p++;
    // } else {
    // return 1;
    // }
    // }

    public ArrayList<ArrayList<Integer>> rankings() { // could be wrong

        ArrayList<ArrayList<Integer>> rankings = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(i);
            arr.add(players.get(i).getScore());
            rankings.add(arr);
        }

        Collections.sort(rankings, new rankingComparator()); // ascending order
        Collections.reverse(rankings); // becomes descending order
        return rankings;

    }

    public ArrayList<Integer> getWinner() { // return arraylist of players - (in case of ties)
        ArrayList<ArrayList<Integer>> ranks = rankings();
        ArrayList<Integer> Winners = new ArrayList<>();
        int playerNumber = ranks.get(0).get(0);
        Winners.add(playerNumber);
        for (int i = 1; i < 4; i++) {
            if (ranks.get(i).get(1) == ranks.get(0).get(1)) {
                Winners.add(ranks.get(i).get(0));
            } else {
                break;
            }
        }
        return Winners;
    }

}

class rankingComparator implements Comparator<ArrayList<Integer>> {

    // override the compare() method
    public int compare(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        if (s1.get(1) == s2.get(1))
            return 0;
        else if (s1.get(1) > s2.get(1))
            return 1;
        else
            return -1;
    }
}
