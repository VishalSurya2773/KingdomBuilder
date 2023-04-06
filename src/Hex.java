public class Hex {
    private Hex left, right, topLeft, topRight, bottomLeft, bottomRight;
    private int x, y;
    private String terrain;
    private Settlement playerSettlement; // stores the settlement (if it's been placed on this specific hex)
    private int radius; 

    public Hex(){
        // default constructor
    }
    public Hex(int xComp, int yComp, String terrainComp) {
        x = xComp;
        y = yComp;
        terrain = terrainComp;
        playerSettlement = null;
        // declare the variable of radius
    }
    public void setAdjacent(){
        /*
         * OXOXOX
         * XOXOXO
         */

        Hex[][] board = Board.getGraph();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(!board[i][j].equals("")){
                    if(i == 0){
                        topLeft = null; 
                        topRight = null;
                    }
                    else if(j == 0){
                        left = null;
                        topLeft = null;
                        bottomLeft = null; 
                    }
                    else if(i == 39){
                        bottomLeft = null;
                        bottomRight = null;
                    }
                    else if(j == 39){
                        right = null;
                        topRight = null;
                        bottomRight = null;
                    }
                    else{
                        
                    }
                }
            }
        }
    }

    public Hex(String string) {
        terrain = string;
    }
    public void setSettlement(Settlement s) {
        playerSettlement = s;
    }

    public void removeSettlement() {
        playerSettlement = null;
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
    public int getRadius(){
        return radius;
    }
    public String getTerrain() {
        return terrain;
    }

}
