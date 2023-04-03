public class Hex {
    private Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y;
    private String terrain;

    public Hex() {
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
