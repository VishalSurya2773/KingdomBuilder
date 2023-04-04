public class Hex {
    private Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y;
    private String terrain;
    private Settlement playerSettlement; // stores the settlement (if it's been placed on this specific hex)

    public Hex(int xComp, int yComp, String terrainComp) {
        x = xComp;
        y = yComp;
        terrain = terrainComp;
    }

    public void setSettlement(Settlement s) {
        playerSettlement = s;
    }

    public int getCenterX() {
        return x;
    }

    public int getCenterY() {
        return y;
    }

    public String getTerrain() {
        return terrain;
    }

}
