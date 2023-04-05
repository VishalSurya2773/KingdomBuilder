public class Hex {
    private Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y;
    private String terrain;
    private Settlement playerSettlement; // stores the settlement (if it's been placed on this specific hex)
    private int radius; 

    public Hex(){
        // default constructor
    }
    public Hex(int xComp, int yComp, String terrainComp) {
        x = xComp;
        y = yComp;
        terrain = terrainComp;
        playerSettlement = null;
        // declare the variable of radius
    }

    public Hex(String string) {
        type  = string;
    }
    public void setSettlement(Settlement s) {
        playerSettlement = s;
    }

    public void removeSettlement() {
        playerSettlement = null;
    }

    public boolean isEmpty() {
        if (playerSettlement == null) {
            return true;
        }
        return false;
    }

    public int getCenterX() {
        return x;
    }

    public int getCenterY() {
        return y;
    }
    public int getRadius(){
        return radius;
    }
    public String getTerrain() {
        return terrain;
    }

}
