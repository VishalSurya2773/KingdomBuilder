public class Settlement {
    private int x, y;
    private String color;
    private Player p;
    private Settlement left, right, topLeft, topRight, bottomLeft, bottomRight;
    private Hex[][] board;

    public Settlement(String color) {
        this.color = color;
        left = null;
        right = null;
        topLeft = null;
        topRight = null;
        bottomLeft = null;
        bottomRight = null;
        board = Board.Graph;
    }

    public Settlement(Player player) {
        p = player;
        color = player.getColor();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return p;
    }

    public String getColor() {
        return color;
    }

    public void findAdjacents() {
        if (board[x + 1][y].playerSettlement() == null) {

        }
    }

    public Hex[] adjacents() {
        return new Hex[6];
    }

}
