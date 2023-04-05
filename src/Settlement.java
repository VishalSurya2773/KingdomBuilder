public class Settlement {
    private int x; private int y;
    private String color;
    private Player p;
    public Settlement(String color){
        this.color = color; 
    }
    public Settlement(Player player){
        p = player;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public Player getPlayer(){
        return p;
    }

}
