import java.util.ArrayList;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed;
    private Card terrainCard; // the card the player has on the current turn
    private int score;
    private boolean isFirst;

    public Player(boolean first) {
        isFirst = first;
    }

    public void setFirst() {
        isFirst = true;
    }

    public void addSettlementToBoard() {

    }

    public void findAdjacentSettlements() {
    }

    public Settlement getSettlement() {
        // random return filler
        return placed.get(0);
    }

    public int getScore() {
        return score;
    }

    public void calculateScore() {

    }

    public void pullCard() {
        terrainCard = Game.getCard();
    }

    public void useSpecialHexTile() {
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
