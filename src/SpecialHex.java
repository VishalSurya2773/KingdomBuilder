public class SpecialHex {
    private String powerType; // lowercase strings
    private int tilesLeft, x, y;
    private boolean isCastle;

    public SpecialHex(int xComp, int yComp, String type, boolean castle) {
        powerType = type;
        tilesLeft = 2;
        x = xComp;
        y = yComp;
        isCastle = castle;
    }

    public SpecialHex giveSpecialTile(Player p) {
        tilesLeft--;
        if (tilesLeft < 0) {
            return null;
        }
        return this;
    }
    public boolean isCastle(){
        return isCastle;
    }
    public int getCenterX() {
        return x;
    }

    public int getCenterY() {
        return y;
    }
    public String getType(){
        return powerType;
    }
    public void barnAction(Player p, Hex h, Settlement s) {
    }

    public void farmAction(Player p, Hex h) {
    }

    public void harborAction(Player p, Hex h, Settlement s) {
    }

    public void paddockAction(Player p, Hex h, Settlement s) {
    }

    public void oasisAction(Player p, Hex h) {
    }

    public void oracleAction(Player p, Hex h) {
    }

    public void tavernAction(Player p, Hex h) {
    }

    public void towerAction(Player p, Hex h) {
        
    }

}
