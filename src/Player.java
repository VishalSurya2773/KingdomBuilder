import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.*;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed; // placed settlements
    // private ArrayList<Settlement> stored;
    private int stored;
    private Hex placeOn;
    public Card terrainCard; // the card the player has on the current turn
    private int score;
    private boolean isFirst;
    private int order;
    private int pNumber;
    private String color;
    public int playerNum;
    private Hex chosenLocationForNewHex;
    public boolean hasDrawn = false, placeSettle = false, useToken = false;

    public Player(boolean first, String clr, int pNum) {
        isFirst = first;
        color = clr;
        // stored = new ArrayList<Settlement>();
        stored = 40;
        // fillStored();
        order = pNum;
        hand = new ArrayList<>();
        placed = new ArrayList<>();
    }

    public void setPlayerNum(int pNum) {
        pNumber = pNum;
    }
    public void setPlaceOn(Hex h){
        placeOn = h;
        placed.add(new Settlement(this, placeOn));
        stored--;
    }
    public Hex getPlaceOn(){
        return placeOn;
    }
    public int getPlayerNum() {
        return pNumber;
    }

    /*
    public void fillStored() {
        for (int i = 0; i < 40; i++) {
            stored.add(new Settlement(this));
        }
    }
    */

    public void setFirst() {
        isFirst = true;
    }

    public boolean canPlace(Hex h, ArrayList<Hex> poss) { // h is the hex the player clicked on, chosenCard is
                                                                    // the terraincard the person chose, idk what s is
                                                                    // but wed can remove it if it's unnecessary
        // checks if the player can place a settlement there
        // if (h.isEmpty() && h.getTerrain().equals(chosenCard.getTerrain())) {
        // // check possible adjacency
        boolean ans = false;
        for(int i = 0; i < poss.size(); i++){
            if(poss.get(i).equals(h)){
                return true;
            }
        }
        
        return false;
    }

    public ArrayList<Hex> getPossible(Board b) {
        ArrayList<Hex> possible = new ArrayList<Hex>();
        boolean empty = true;
        System.out.println("Placed: " + placed.size());
        for (int i = 0; i < placed.size(); i++) {
            if (placed.get(i).placedOn().getTerrain().equals(terrainCard.getTerrain())) {
                // make sure that the of possible hexes doesnt contain already occupied hexes
                System.out.println("Possible Hexes: ");
                Hex[] hexes = placed.get(i).placedOn().adjacents();
                
                for(int j = 0; j < hexes.length; j++){
                    if(hexes[j] == null) System.out.print(" Null, ");
                    else System.out.print(hexes[j].getTerrain() + ", ");
                }

                for (int j = 0; j < hexes.length; j++) {
                    if (hexes[j] != null && hexes[j].getTerrain().equals(terrainCard.getTerrain()) && hexes[j].getAvail()) {
                        possible.add(hexes[j]);
                        System.out.println("POSS HEX UPDATED");
                        empty = false;
                    }
                }
            }
        }
        if (empty) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 40; j++) {
                    if (!b.getGraph()[i][j].getTerrain().equals("blank")) {
                        if (b.getGraph()[i][j].getTerrain().equals(terrainCard.getTerrain())) {
                            possible.add(b.getGraph()[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println("POSSIBLE SIZE: " + possible.size());
        return possible;
    }

    public Settlement getSettlementFromBoard() {
        // random return filler
        return placed.get(0);
    }

    /* 
    public Settlement getSettlementFromStore(Game g) {
        if (stored.size() > 0) {
            return stored.remove(0);
        }
        g.gameOver = true;
        return null;
    }
    */

    public boolean isFirst() {
        return isFirst;
    }

    public ArrayList<SpecialHex> getHand() {
        return hand;
    }

    public int numSettlements() {
        return stored;
    }

    public int getOrder() {
        return order;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public Card getTerrainCard() {
        return terrainCard;
    }

    public SpecialHex chooseHex() {
        return new SpecialHex("barn");
    }

    public void chooseTerrainHex(Hex x) {
        chosenLocationForNewHex = x;
    }

    public Hex chosenTerrainHex() {
        return chosenLocationForNewHex;
    }

    public void drawObjectiveCard() {

    }

    public void calculateScore(Game g) {
        int total = 0;
        ObjectiveCard first = g.objectives.get(0);
        ObjectiveCard scnd = g.objectives.get(1);
        ObjectiveCard thrd = g.objectives.get(2);

        total += first.getScore(color, g);
        total += scnd.getScore(color, g);
        total += thrd.getScore(color, g);
        score = total;
    }

    public void drawCard(Game g) {
        terrainCard = g.getCard();
    }

    public void useSpecialHexTile(SpecialHex sh) { // should be clicked
        String type = sh.getType();
        if (type.equals("barn")) { // Player p, Hex h, Settlement s (s.getPlayer(), h, s)
            // code after you find out which settlement they choose
        } else if (type.equals("paddock")) {

        } else if (type.equals("oracle")) {

        } else if (type.equals("farm")) {

        } else if (type.equals("tower")) {

        } else if (type.equals("tavern")) {

        } else if (type.equals("harbor")) {

        }
        // else if(sh.isCastle()){

        // }

    }

    public boolean isSameTerrain() {
        return chosenTerrainHex().getTerrain().equals(terrainCard.getTerrain());
    }

    public void addSpecialHexTile(SpecialHex x) {
        hand.add(x);
        if (x.getTerrain().equals("barn")) {
        }
    }

    public void removeSpecialHexTile(SpecialHex x) {
        int pl = hand.indexOf(x);
        if (pl >= 0) {
            hand.remove(pl);
        }
    }

    public void barnAction(Game g) {
        // Settlement s = getSettlementFromStore(g);
        /*
        if (canPlace(s, chosenTerrainHex(), terrainCard)) { // error cuz parameters - fix later
            placeSettlement(s, chosenTerrainHex(), terrainCard);
            System.out.println("Success");
        } else {
            System.out.println("failed");
        }
        */
        return;

    }

    public TreeMap<Hex, int[]> showAvailForFarm() {
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();

        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("grass")
                        && Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    Hex[] adj = Game.gameBoard.getGraph()[r][c].adjacents();
                    for (Hex h : adj) {
                        if (h.getSettlement().getPlayer().equals(this)) {
                            avail.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                            avail.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                            avail.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                        }
                    }
                }
            }
        }
        if (avail.keySet().size() > 0) {
            return avail;
        }
        TreeMap<Hex, int[]> sec = new TreeMap<Hex, int[]>();
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].isEmpty()
                        && Game.gameBoard.getGraph()[r][c].getTerrain().equals("grass")) {
                    sec.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                    sec.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                    sec.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                }
            }
        }

        return sec;

    }

    public void farmAction(Hex h, Game g) {
        // h.setSettlement(getSettlementFromStore(g));
        return;
    }

    public TreeMap<Hex, ArrayList<Integer>> showAvailForHarborAction() {
        TreeMap<Hex, ArrayList<Integer>> avail = new TreeMap<Hex, ArrayList<Integer>>();

        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("water")
                        && Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    Hex[] adj = Game.gameBoard.getGraph()[r][c].adjacents();
                    for (Hex h : adj) {
                        if (h.getSettlement().getPlayer().equals(this)) {
                            avail.put(Game.gameBoard.getGraph()[r][c], new ArrayList<Integer>());
                            avail.get(Game.gameBoard.getGraph()[r][c]).set(0, r);
                            avail.get(Game.gameBoard.getGraph()[r][c]).set(1, c);
                        }
                    }
                }
            }
        }

        if (avail.keySet().size() > 0) {
            return avail;
        }

        TreeMap<Hex, ArrayList<Integer>> sec = new TreeMap<Hex, ArrayList<Integer>>();
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("water") &&
                        Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    ArrayList<Integer> x = new ArrayList<Integer>();
                    x.add(r);
                    x.add(c);
                    sec.put(Game.gameBoard.getGraph()[r][c], x);
                }
            }
        }
        return sec;

    }

    public void harborAction(Hex h, Settlement s) {
        h.setSettlement(s);
        return;
    }

    public TreeMap<Hex, int[]> showAvailForPaddock(int r, int c) {
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();
        int[] cord = new int[2];
        if (Game.gameBoard.isValid(r + 2, c) && Game.gameBoard.getGraph()[r + 2][c].isEmpty()) {
            cord[0] = r + 2;
            cord[1] = c;
            avail.put(Game.gameBoard.getGraph()[r + 2][c], cord);
        }
        if (Game.gameBoard.isValid(r - 2, c) && Game.gameBoard.getGraph()[r - 2][c].isEmpty()) {
            cord[0] = r - 2;
            cord[1] = c;
            avail.put(Game.gameBoard.getGraph()[r - 2][c], cord);
        }
        if (Game.gameBoard.isValid(r, c + 2) && Game.gameBoard.getGraph()[r][c + 2].isEmpty()) {
            cord[0] = r;
            cord[1] = c + 2;
            avail.put(Game.gameBoard.getGraph()[r][c + 2], cord);
        }
        if (Game.gameBoard.isValid(r, c - 2) && Game.gameBoard.getGraph()[r][c - 2].isEmpty()) {
            cord[0] = r;
            cord[1] = c - 2;
            avail.put(Game.gameBoard.getGraph()[r][c - 2], cord);
        }
        return avail;

    }

    public void paddockAction(Hex original, Hex newHex, Settlement s) {

        original.setSettlement(null);
        return;
    }

    public TreeMap<Hex, int[]> showAvailForOasis() {
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();

        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("desert")
                        && Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    Hex[] adj = Game.gameBoard.getGraph()[r][c].adjacents();
                    for (Hex h : adj) {
                        if (h.getSettlement().getPlayer().equals(this)) {
                            avail.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                            avail.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                            avail.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                        }
                    }
                }
            }
        }

        if (avail.keySet().size() > 0) {
            return avail;
        }
        TreeMap<Hex, int[]> sec = new TreeMap<Hex, int[]>();
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("desert")
                        && Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    sec.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                    sec.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                    sec.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                }
            }
        }
        return sec;
    }

    public void oasisAction(Hex h, Game g) {
        // Settlement s = getSettlementFromStore(g);
        // h.setSettlement(s);
        return;
    }

    public TreeMap<Hex, int[]> showAvailForOracle(String terrain) {
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();

        // checking to see if there are any adjacents or something like that

        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getSettlement().getPlayer().equals(this)) {
                    Hex[] adj = Game.gameBoard.getGraph()[r][c].adjacents();
                    for (Hex h : adj) {
                        if (h.isEmpty() && h.getTerrain().equals(terrain)) {
                            avail.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                            avail.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                            avail.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                        }
                    }
                }
            }
        }

        if (avail.keySet().size() > 0) {
            return avail;
        }

        // in the event that there isnt any adjacent stuff - it will j highlight
        // whatever it can possibly do.
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals(terrain)
                        && Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    avail.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                    avail.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                    avail.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                }
            }
        }
        return avail;
    }

    public void oracleAction(Hex h, Game g) {
        // Settlement s = getSettlementFromStore(g);
        // h.setSettlement(s);
        return;

    }

    public boolean tav(int r, int c) {
        /*
        if (Game.gameBoard.getGraph()[r][c].isEmpty()
                && Game.gameBoard.getGraph()[r][c - 1].getSettlement().equals(stored.get(0))
                && Game.gameBoard.getGraph()[r][c - 2].getSettlement().equals(stored.get(0))
                && Game.gameBoard.getGraph()[r][c - 3].getSettlement().equals(stored.get(0))) {
            return true;
        }
        if (Game.gameBoard.getGraph()[r][c].isEmpty()
                && Game.gameBoard.getGraph()[r][c + 1].getSettlement().equals(stored.get(0))
                && Game.gameBoard.getGraph()[r][c + 2].getSettlement().equals(stored.get(0))
                && Game.gameBoard.getGraph()[r][c + 3].getSettlement().equals(stored.get(0))) {
            return true;
        }
         */
        
        return false;
        /*
         * if (Game.gameBoard.getGraph()[r][c].isEmpty()) {
         * return;
         * }
         * 
         * TreeSet<Hex> taken = new TreeSet<Hex>();
         * taken.add(Game.gameBoard.getGraph()[r][c]);
         * if (Game.gameBoard.getGraph()[r][c +
         * 1].getSettlement().equals(stored.get(0))) {
         * taken.addAll(tav(r, c + 1));
         * }
         * if (Game.gameBoard.getGraph()[r][c -
         * 1].getSettlement().equals(stored.get(0))) {
         * taken.addAll(tav(r, c - 1));
         * }
         * if(taken.size())
         * return taken;
         * }
         */

    }

    public void tavernAction(Hex h, Game g) {
        // Settlement s = getSettlementFromStore(g);
        // h.setSettlement(s);
        return;
    }

    public TreeMap<Hex, int[]> showAvailForTower() {
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();
        TreeMap<Hex, int[]> adj = new TreeMap<Hex, int[]>();
        for (int c = 0; c < Game.gameBoard.getGraph()[0].length; c++) {
            if (!Game.gameBoard.getGraph()[0][c].getTerrain().equals("") && Game.gameBoard.getGraph()[0][c].isEmpty()) {
                avail.put(Game.gameBoard.getGraph()[0][c], new int[2]);
                avail.get(Game.gameBoard.getGraph()[0][c])[0] = 0;
                avail.get(Game.gameBoard.getGraph()[0][c])[1] = c;
            }
        }

        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            if (!Game.gameBoard.getGraph()[r][0].getTerrain().equals("") && Game.gameBoard.getGraph()[r][0].isEmpty()) {
                avail.put(Game.gameBoard.getGraph()[r][0], new int[2]);
                avail.get(Game.gameBoard.getGraph()[r][0])[0] = r;
                avail.get(Game.gameBoard.getGraph()[r][0])[1] = 0;
            }
        }
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            if (!Game.gameBoard.getGraph()[r][Game.gameBoard.getGraph()[0].length - 1].getTerrain().equals("")
                    && Game.gameBoard.getGraph()[r][Game.gameBoard.getGraph()[0].length - 1].isEmpty()) {
                avail.put(Game.gameBoard.getGraph()[r][Game.gameBoard.getGraph()[0].length - 1], new int[2]);
                avail.get(Game.gameBoard.getGraph()[r][Game.gameBoard.getGraph()[0].length - 1])[0] = r;
                avail.get(Game.gameBoard.getGraph()[r][Game.gameBoard.getGraph()[0].length - 1])[1] = Game.gameBoard
                        .getGraph()[0].length - 1;
            }
        }

        for (int c = 0; c < Game.gameBoard.getGraph()[0].length; c++) {
            if (!Game.gameBoard.getGraph()[Game.gameBoard.getGraph().length - 1][c].getTerrain().equals("")
                    && Game.gameBoard.getGraph()[Game.gameBoard.getGraph().length - 1][c].isEmpty()) {
                avail.put(Game.gameBoard.getGraph()[0][c], new int[2]);
                avail.get(Game.gameBoard.getGraph()[0][c])[0] = 0;
                avail.get(Game.gameBoard.getGraph()[0][c])[1] = c;
            }
        }

        // to find adjacencies
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getSettlement().getPlayer().equals(this)) {
                    adj.put(Game.gameBoard.getGraph()[r][c], new int[2]);
                    adj.get(Game.gameBoard.getGraph()[r][c])[0] = r;
                    adj.get(Game.gameBoard.getGraph()[r][c])[1] = c;
                }
            }
        }

        TreeMap<Hex, int[]> result = new TreeMap<Hex, int[]>(avail);
        result.keySet().retainAll(adj.keySet());
        return result;
    }

    public void towerAction(Hex h, Game g) {
        // Settlement s = this.getSettlementFromStore(g);
        // h.setSettlement(s);
        return;

    }
    public Hex findHex(int x, int y, Game g) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                if (g.gameBoard.getGraph()[i][j].isEmpty()) {
                    if(g.gameBoard.getGraph()[i][j].isClicked(x, y)){ // isClicked() isnt implemented
                        return g.gameBoard.getGraph()[i][j];
                    }
                }
            }
        }
        return null;
    }
}
