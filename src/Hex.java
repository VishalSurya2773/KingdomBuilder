public class Hex {
    private Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x1, y1;
    private String terrain;

    public Hex(int horizontal, int vertical,  String t) {
        x1 = horizontal;
        y1 = vertical;
        terrain = t;
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
