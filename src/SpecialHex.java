public class SpecialHex extends Hex {
    private String powerType; // lowercase strings
    private int tilesLeft, x, y;
    private boolean isCastle;

    public SpecialHex(int xComp, int yComp, String type, boolean castle) {
        super(xComp, yComp, type);
        powerType = type;
        tilesLeft = 2;
        x = xComp;
        y = yComp;
        isCastle = castle;
    }

    public SpecialHex(String s) {
        super(s);
    }

    public SpecialHex giveSpecialTile(Player p) {
        tilesLeft--;
        if (tilesLeft < 0) {
            return null;
        }
        return this;
    }

    public boolean isCastle() {
        return isCastle;
    }

    @Override
    public int getCenterX() {
        return x;
    }

    @Override
    public int getCenterY() {
        return y;
    }

    public String getType() {
        return powerType;
    }

    public String toString() {
        return powerType;
    }

}
//