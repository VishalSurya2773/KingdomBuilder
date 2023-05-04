public class Hex {
    public Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y;
    private String terrain;
    private Settlement playerSettlement; // stores the settlement (if it's been placed on this specific hex)
    private int radius;
    private boolean isAvail;

    public Hex() {
        // default constructor
    }

    public Hex(int xComp, int yComp) {
        x = xComp;
        y = yComp;
        playerSettlement = null;
        // declare the variable of radius
    }

    public Hex(int xComp, int yComp, String terrainType) {
        x = xComp;
        y = yComp;
        terrain = terrainType;
    }

    public Hex(String terrainType) {
        terrain = terrainType;
        x = 0;
        y = 0;
        // left = null;
        // right = new Hex("");
        // topRight = new Hex("");
        // topLeft = new Hex("");
        // bottomRight = new Hex("");
        // bottomLeft = new Hex("");
    }

    public int compareTo(Hex cmp) {
        if (cmp == null) {
            return Integer.MIN_VALUE;
        }
        if (cmp.getCenterX() == x)
            return cmp.getCenterY() - y;
        return cmp.getCenterX() - x;
    }

    public void setAvail(boolean x) {
        isAvail = x;

    }

    public boolean getAvail() {
        return isAvail;
    }

    public void setAdjacent() {
        /*
         * OXOXOX
         * XOXOXO
         */

        Hex[][] board = Game.gameBoard.getGraph();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.compareTo(board[i][j]) == 0) { // to check if it's the same hex

                    if (i == 0) {
                        topLeft = null;
                        topRight = null;
                    } 
                    if (j == 0) {
                        left = null;
                        topLeft = null;
                        bottomLeft = null;
                    } 
                    if (i == 39) {
                        bottomLeft = null;
                        bottomRight = null;
                    } 
                    if (j == 39) {
                        right = null;
                        topRight = null;
                        bottomRight = null;
                    }

                    if (left != null) {
                        left = board[i][j - 2];
                    } 
                    if (right != null) {
                        right = board[i][j + 2];
                    } 
                    if (topLeft != null) {
                        topLeft = board[i - 1][j - 1];
                    } 
                    if (topRight != null) {
                        topRight = board[i - 1][j + 1];
                    } 
                    if (bottomLeft != null) {
                        bottomLeft = board[i + 1][j - 1];
                    } 
                    if (bottomRight != null) {
                        bottomRight = board[i + 1][j + 1];
                    }

                    break;
                }
            }
        }
    }

    public Hex[] adjacents() {
        Hex[] out = { bottomLeft, left, topLeft, topRight, right, bottomRight };
        return out;
    }

    public Hex returnDirection(String dir) {
        if (dir.equals("bottomLeft")) {
            return bottomLeft;
        } else if (dir.equals("left")) {
            return left;
        } else if (dir.equals("topLeft")) {
            return topLeft;
        } else if (dir.equals("topRight")) {
            return topRight;
        } else if (dir.equals("right")) {
            return right;
        } else if (dir.equals("bottomRight")) {
            return left;
        }
        return null;
    }

    public void setSettlement(Settlement s) {
        playerSettlement = s;
        playerSettlement.setHex(this);
        isAvail = false;
    }

    public void removeSettlement() {
        playerSettlement = null;
    }

    public Settlement getSettlement() {
        return playerSettlement;
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

    public void setX(int num) {
        x = num;
    }

    public void setY(int num) {
        y = num;
    }

    public int getRadius() {
        return radius;
    }

    public String getTerrain() {
        return terrain;
    }

    public String toString() {
        return terrain;
    }
    public boolean isClicked(int mouseX, int mouseY){
        // if() // stuff
        return false;
    }

}
