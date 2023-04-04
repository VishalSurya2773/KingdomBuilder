import java.util.ArrayList;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed;
    private Card terrainCard; // the card the player has on the current turn
    private int score;
    private boolean isFirst;
    private int order;
    private String color;

    public Player(boolean first, String clr, int or) {
        isFirst = first;
        color = clr;
        order = or;
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

    public Settlement getSettlement() {
        // random return filler
        return placed.get(0);
    }

    public int getOrder() {
        return order;
    }

    public int getScore() {
        return score;
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
        if(type.equals())
    }

    public void addSpecialHexTile(SpecialHex x) {
        hand.add(x);
    }

    public void removeSpecialHexTile(SpecialHex x) {
        int pl = hand.indexOf(x);
        if (pl >= 0) {
            hand.remove(pl);
        }
    }

}
