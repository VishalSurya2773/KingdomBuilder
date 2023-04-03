import java.util.ArrayList;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed;
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
        return 0;
    }

    public void calculateScore() {
        // find all cards
        // check each card for score of player ex: farmer method returns int for that
        // score
        // add all scores together
    }

    public void pullCard() {
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
