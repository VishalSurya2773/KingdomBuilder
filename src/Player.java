import java.util.ArrayList;


public class Player {
   private ArrayList<SpecialHex> hand;
   private ArrayList<Settlement> placed;
   private ArrayList<Settlement> stored;
   private Card terrainCard; // the card the player has on the current turn
   private int score;
   private boolean isFirst;
   private int order;
   private String color;
   private Hex chosenLocationForNewHex;


   public Player(boolean first, String clr, int or) {
       isFirst = first;
       color = clr;
       order = or;
       stored = new ArrayList<Settlement>();
       fillStored();
   }


   public void fillStored(){
       for(int i = 0; i<40; i++){
           stored.add(new Settlement(color));
       }
   }


   public void setFirst() {
       isFirst = true;
   }


   public void placeSettlement(Settlement s, Hex h) {
       if (canPlace(s, h)) {
           placeSettlement(s, h); // places it logically on the board
           // then place it graphically on the board
       }
   }


   public boolean canPlace(Settlement s, Hex h) {
       // checks if the player can place a settlement there
       if (h.isEmpty()) {
           // check possible adjacency
       }
       return false;
   }


   public Settlement getSettlementFromBoard() {
       // random return filler
       return placed.get(0);
   }


   public Settlement getSettlementFromStore(){
       if(stored.size() > 0){
           return stored.remove(0);
       }
       else{
            Game.gameOver = true; 
       }
   }

   

   public int numSettlements(){
    return stored.size();
   }




   public int getOrder() {
       return order;
   }


   public int getScore() {
       return score;
   }
   
   public Card getTerrain(){
       return terrainCard;
   }


   public SpecialHex chooseHex(){
       return new SpecialHex("barn");
   }
   public void chooseTerrainHex(Hex x){
       chosenLocationForNewHex = x;
   }
   public Hex  chosenTerrainHex(){
       return chosenLocationForNewHex;
   }


   public void calculateScore() {
       int total = 0;
       ObjectiveCard first = Game.objectives.get(0);
       ObjectiveCard scnd = Game.objectives.get(1);
       ObjectiveCard thrd = Game.objectives.get(2);


       total += first.getScore(color);
       total += scnd.getScore(color);
       total += thrd.getScore(color);
       score = total;
   }


   public void drawCard() {
       terrainCard = Game.getCard();
   }


   public void useSpecialHexTile(SpecialHex sh) { // should be clicked
       String type = sh.getType();
       if(type.equals("barn")){ // Player p, Hex h, Settlement s (s.getPlayer(), h, s)
           // code after you find out which settlement they choose
       }
       else if(type.equals("paddock")){


       }
       else if(type.equals("oasis")){


       }
       else if(type.equals("oracle")){


       }
       else if(type.equals("farm")){


       }
       else if(type.equals("tower")){


       }
       else if(type.equals("tavern")){


       }
       else if(type.equals("harbor")){


       }
       // else if(sh.isCastle()){
          
       // }


      
   }


   public boolean isSameTerrain(){
       return chosenTerrainHex().getTerrain().equals(terrainCard.getTerrain());
   }


   public void addSpecialHexTile(SpecialHex x) {
       hand.add(x);
       if(x.getTerrain().equals("barn")){
       }
   }


   public void removeSpecialHexTile(SpecialHex x) {
       int pl = hand.indexOf(x);
       if (pl >= 0) {
           hand.remove(pl);
       }
   }


   public void barnAction( ) {
       Settlement s = getSettlementFromStore();
       if(canPlace(s, chosenTerrainHex())){
           placeSettlement(s, chosenTerrainHex());
           System.out.println("Success");
       }
       else{
        System.out.println("failed");
       }
      
   }


   public void farmAction( Hex h) {

   }


   public void harborAction( Hex h, Settlement s) {
   }


   public void paddockAction( Hex h, Settlement s) {
   }


   public void oasisAction( Hex h) {
   }


   public void oracleAction( Hex h) {
   }


   public void tavernAction( Hex h) {
   }


   public void towerAction(, Hex h) {


   }








}



