import java.util.ArrayList;
import java.util.TreeMap;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed;
    private ArrayList<Settlement> stored;
    private Card terrainCard; // the card the player has on the current turn
    private int score;
    private boolean isFirst;
    private int order;
    private String color;
    private int playerNum; 
    private Hex chosenLocationForNewHex;


    public Player(boolean first, String clr, int playerNum) {
        isFirst = first;
        color = clr;
        stored = new ArrayList<Settlement>();
        fillStored();
        this.playerNum = playerNum;

    }

    public void fillStored() {
        for (int i = 0; i < 40; i++) {
            stored.add(new Settlement(color));
        }
    }

    public void setFirst() {
        isFirst = true;
    }

    public void placeSettlement(Settlement s, Hex h) {
        if (canPlace(s, h)) {
            placeSettlement(s, h); // places it logically on the board
            // then place it graphically on the board
        }
    }

    public boolean canPlace(Settlement s, Hex h) {
        // checks if the player can place a settlement there
        if (h.isEmpty()) {
            // check possible adjacency
        }
        return false;
    }

    public Settlement getSettlementFromBoard() {
        // random return filler
        return placed.get(0);
    }

    public Settlement getSettlementFromStore() {
        if (stored.size() > 0) {
            return stored.remove(0);
        }
        Game.gameOver = true;
        return null;
    }

    public int numSettlements() {
        return stored.size();
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

    public Card getTerrain() {
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

    public void drawObjectiveCard(){

    }

    public void calculateScore() {
        int total = 0;
        ObjectiveCard first = Game.objectives.get(0);
        ObjectiveCard scnd = Game.objectives.get(1);
        ObjectiveCard thrd = Game.objectives.get(2);

        total += first.getScore(color);
        total += scnd.getScore(color);
        total += thrd.getScore(color);
        score = total;
    }

    public void drawCard() {
        terrainCard = Game.getCard();
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

    public void barnAction() {
        Settlement s = getSettlementFromStore();
        if (canPlace(s, chosenTerrainHex())) {
            placeSettlement(s, chosenTerrainHex());
            System.out.println("Success");
        } else {
            System.out.println("failed");
        }

    }

    public ArrayList<Hex> showAvailForFarmAction(Hex h) {
        ArrayList<Hex> availableHexList = new ArrayList<Hex>();
        // edge case where 0 adjecent hexes
        for (int i = 0; i < placed.size(); i++) {
            Hex[] adj = placed.get(i).placedOn().adjacents();
            for (int j = 0; j < adj.length; j++) {
                if (adj[j].isEmpty() && adj[j].getTerrain().equals("grass")) {
                    availableHexList.add(adj[j]);
                }
            }
        }
        if (availableHexList.size() == 0) {
            for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
                for (int c = 0; c < Game.gameBoard.Graph[r].length; c++) {
                    if (Game.gameBoard.Graph[r][c].getTerrain().equals("grass")) {
                        availableHexList.add(Game.gameBoard.getGraph()[r][c]);
                    }
                }
            }
        }
        return availableHexList;
    }

    public void farmAction(Hex h) {
        h.setSettlement(getSettlementFromStore());
    }

    public TreeMap<Hex, ArrayList<Integer>> showAvailForHarborAction() {
        TreeMap<Hex, ArrayList<Integer>> avail = new TreeMap<Hex, ArrayList<Integer>>();
        for (int r = 0; r < Game.gameBoard.getGraph().length; r++) {
            for (int c = 0; c < Game.gameBoard.getGraph()[r].length; c++) {
                if (Game.gameBoard.getGraph()[r][c].getTerrain().equals("water") &&
                        Game.gameBoard.getGraph()[r][c].isEmpty()) {
                    ArrayList<Integer> x = new ArrayList<Integer>();
                    x.add(r);
                    x.add(c);
                    avail.put(Game.gameBoard.getGraph()[r][c], x);
                }
            }
        }
        return avail;

    }

    
    public void harborAction(Hex h, Settlement s) {
        h.setSettlement(s);
        return;
    }

    public TreeMap<Hex, int[]> showAvailForPaddock(int r, int c){
        TreeMap<Hex, int[]> avail = new TreeMap<Hex, int[]>();
        int[] cord = new int[2];
        if(Game.gameBoard.isValid(r+2, c) && Game.gameBoard.getGraph()[r+2][c].isEmpty()){
            cord[0] = r+2;
            cord[1] = c;
            avail.put(Game.gameBoard.getGraph()[r+2][c], cord  );
        }
         if(Game.gameBoard.isValid(r-2, c) && Game.gameBoard.getGraph()[r-2][c].isEmpty()){
            cord[0] = r-2;
            cord[1] = c;
            avail.put(Game.gameBoard.getGraph()[r-2][c], cord  );
        }
        if(Game.gameBoard.isValid(r, c+2) && Game.gameBoard.getGraph()[r][c+2].isEmpty()){
            cord[0] = r;
            cord[1] = c+2;
            avail.put(Game.gameBoard.getGraph()[r][c+2], cord  );
         }
        if(Game.gameBoard.isValid(r, c-2) && Game.gameBoard.getGraph()[r][c-2].isEmpty()){
            cord[0] = r;
            cord[1] = c-2;
            avail.put(Game.gameBoard.getGraph()[r][c-2], cord  );
        }
        return avail;

    }
    public void paddockAction(Hex original, Hex new, Settlement s) {
        h.setSettlement(s);
        return;
    }

    public ArrayList<Settlement> oasisAction(Hex h) {
        return new ArrayList<Settlement>();
    }

    public void oracleAction(Hex h) {
    }

    public void tavernAction(Hex h) {
    }

    public void towerAction(Hex h) {

    }

}
