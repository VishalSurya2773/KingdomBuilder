import java.util.*;
import java.io.*;

public class Hex {
    public Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y, numSpecialTilesLeft;
    private String terrain;
    private Settlement playerSettlement; // stores the settlement (if it's been placed on this specific hex)
    private int radius;
    private boolean isAvail, isSpecialHex, isCastle;
    private int[] xPoints;
    private int[] yPoints;

    public Hex() {
        // default constructor
    }

    public Hex(int xComp, int yComp) {
        x = xComp;
        y = yComp;
        playerSettlement = null;

    }

    public Hex(int xComp, int yComp, String terrainType) {
        x = xComp;
        y = yComp;
        terrain = terrainType;
        if (terrain.equals("castle")) {
            isCastle = true;
        } else {
            isCastle = false;
        }
        findSpecialHex(terrainType);

    }

    public Hex(String terrainType) {
        terrain = terrainType;
        x = 0;
        y = 0;
        if (terrain.equals("castle")) {
            isCastle = true;
        } else {
            isCastle = false;
        }
        findSpecialHex(terrainType);
        // left = null;
        // right = new Hex("");
        // topRight = new Hex("");
        // topLeft = new Hex("");
        // bottomRight = new Hex("");
        // bottomLeft = new Hex("");
    }

    public void setArray() {
        xPoints = new int[] { x - 20, x, x + 20, x + 20, x, x - 20 };
        yPoints = new int[] { y - 10, y - 22, y - 10, y + 10, y + 22, y + 10 };
    }

    public void findSpecialHex(String x) {
        if (x.equals("oracle") || x.equals("farm") || x.equals("tower") || x.equals("tavern") || x.equals("barn")
                || x.equals("harbor") || x.equals("paddock") || x.equals("oasis")) {
            isSpecialHex = true;
            numSpecialTilesLeft = 2;
        } else {
            isSpecialHex = false;
        }
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
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

    public boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= 20 || c >= 40)
            return false;
        return true;
    }

    public Hex giveSpecialTile(Player p) {
        if (!isSpecialHex) {
            return null;
        }
        numSpecialTilesLeft--;
        if (numSpecialTilesLeft < 0) {
            return null;
        }
        return this;
    }

    public void setAdjacent(int i, int j, Hex[][] board) {
        /*
         * OXOXOX
         * XOXOXO
         */
        if (isValid(i, j - 2))
            left = board[i][j - 2];

        if (isValid(i, j + 2))
            right = board[i][j + 2];

        if (isValid(i - 1, j - 1))
            topLeft = board[i - 1][j - 1];

        if (isValid(i - 1, j + 1))
            topRight = board[i - 1][j + 1];

        if (isValid(i + 1, j - 1))
            bottomLeft = board[i + 1][j - 1];

        if (isValid(i + 1, j + 1))
            bottomRight = board[i + 1][j + 1];

    }

    public Hex[] adjacents() {
        Hex[] out = { bottomLeft, left, topLeft, topRight, right, bottomRight };
        return out;
    }

    public void printAdjacent() {
        Hex[] out = { bottomLeft, left, topLeft, topRight, right, bottomRight };
        System.out.println(Arrays.toString(out));
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

    public boolean isSpecialHex() {
        return isSpecialHex;
    }

    public String toString() {
        return terrain;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        // if() // stuff
        int intersections = 0;

        for (int i = 0; i < xPoints.length; i++) {
            double x1 = xPoints[i];
            double y1 = yPoints[i];
            double x2 = xPoints[(i + 1) % xPoints.length];
            double y2 = yPoints[(i + 1) % yPoints.length];

            if (((y1 > mouseY) != (y2 > mouseY))
                    && (mouseX < (x2 - x1) * (mouseY - y1) / (y2 - y1) + x1)) {
                intersections++;
            }
        }

        return (intersections % 2 != 0);
    }

}

class sortHex implements Comparator<Hex> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Hex a, Hex b) {
        return a.getCenterX() - b.getCenterX();
    }
}
