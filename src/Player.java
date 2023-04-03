import java.util.ArrayList;

public class Player {
    private ArrayList<SpecialHex> hand;
    private ArrayList<Settlement> placed;
    private Card terrainCard; // the card the player has on the current turn
    private int score;
    private boolean isFirst;
    private int order;
    public Player(boolean first) {
        isFirst = first;
    }

    public void setFirst() {
        isFirst = true;
    }
    public void placedSettlement(Settlement s){
        if(possibleSettlement(s)){
            addSettlementToBoard();

        }
    }
    public void addSettlementToBoard() {

    }

    public boolean possibleSettlement(Settlement s) {
        //checks if the player can place a settlement there
        return false;
    }

    public Settlement getSettlement() {
        // random return filler
        return placed.get(0);
    }
    public int getOrder(){
        return order;
    }
    public int getScore() {
        return score;
    }

    public void calculateScore() {

    }

    public void drawCard() {
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
