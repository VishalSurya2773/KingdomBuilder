public class Settlement {
    private int x, y;
    private String color;
    private Player p;
    private Settlement left, right, topLeft, topRight, bottomLeft, bottomRight;
    private Board board;
    private Hex[][] hexes;

    public Settlement(String color) {
        this.color = color;
        right = null;
        topLeft = null;
        topRight = null;
        bottomLeft = null;
        bottomRight = null;
        board = Game.gameBoard;
        hexes = board.Graph;
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
        for (int r = 0; r < hexes.length; r++) {
            for (int c = 0; c < hexes[r].length; c++) {
                // if(compareTo(hexes[r][c]) == 0); try to do opp
                if (board.isValid(r, c)) {
                    if (r == 0) {
                        topLeft = null;
                        topRight = null;
                    } else if (c == 0) {
                        bottomLeft = null;
                        left = null;
                        topLeft = null;
                    } else if (r == 39) {
                        bottomLeft = null;
                        bottomRight = null;
                    } else if (c == 39) {
                        bottomRight = null;
                        right = null;
                        topRight = null;
                    }

                    if (left == null) {

                    }

                }
            }
        }

        if (board.isValid(x - 2, y) && hexes[x - 2][y].getSettlement() == null) {
            left = null;
        } else {
            left = hexes[x - 2][y].getSettlement();
        }
    }

    public Hex[] adjacents() {
        return new Hex[6];
    }

}
