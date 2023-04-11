public class Settlement {
    private int x, y;
    private String color;
    private Player p;
    private Settlement left, right, topLeft, topRight, bottomLeft, bottomRight;
    private Board board;

    public Settlement(String color) {
        this.color = color;
        right = null;
        topLeft = null;
        topRight = null;
        bottomLeft = null;
        bottomRight = null;
        board = = Game.gameBoard;
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
        if (board.isValid(x - 2, y) && board[x - 2][y].getSettlement() == null) {
            left = null;
        } else {
            left = board[x - 2][y].getSettlement();
        }

        if (board[x + 2][y].getSettlement().isValid() && board[x + 2][y].getSettlement() == null) {
            right = null;
        } else {
            right = board[x + 2][y].getSettlement();
        }

        if (board[x - 2][y - 2].getSettlement().isValid() && board[x - 2][y - 2].getSettlement() == null) {
            topLeft = null;
        } else {
            topLeft = board[x - 2][y - 2].getSettlement();
        }

    }

    public Hex[] adjacents() {
        return new Hex[6];
    }

}
