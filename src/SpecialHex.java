public class SpecialHex extends Hex {
    private String powerType;
    private int tilesLeft;

    public SpecialHex(String type) {
        powerType = type;
        tilesLeft = 2;
    }

    public SpecialHex giveSpecialTile(Player p) {
        return this;
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
