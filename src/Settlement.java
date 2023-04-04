public class Settlement {
    private int x; private int y;
    private Player p;
    public Settlement(Player player){
        p = player;
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
