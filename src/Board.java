import java.util.TreeMap;
import java.util.*;
import java.io.*;

public class Board {
    private Hex[][] Graph;
    private TreeMap<String, Hex> map;
    private ArrayList<Scanner> boardScanners;

    public Board() {
        Graph = new Hex[40][40]; // tentative values
        map = new TreeMap<int, Hex>();
        map.set(0, new Hex("sand"));
        map.set(1, new Hex("canyon"));
        map.set(2, new Hex("mountain"));
        map.set(3, new Hex("flower"));
        map.set(4, new Hex("forest"));
        map.set(5, new Hex("water"));
        map.set(6, new Hex("grass"));
        map.set(7, new SpecialHex("oracle"));
        map.set(8, new SpecialHex("farm"));
        map.set(9, new SpecialHex("tower"));
        map.set(10, new SpecialHex("tavern"));
        map.set(11, new SpecialHex("barn"));
        map.set(12, new Specialhex("harbor"));
        map.set(13, new SpecialHex("paddock"));
        map.set(14, new SpecialHex("castle"));
        map.set(15, new SpecialHex("oasis"));
        boardScanners = new ArrayList<Scanner>();
    }

    public void makeGraph() throws IOException {
       boardScanners.add(new Scanner(new File("RandomBoard1.txt")));
       boardScanners.add(new Scanner(new File("RandomBoard2.txt")));
       boardScanners.add(new Scanner(new File("RandomBoard3.txt")));
       boardScanners.add(new Scanner(new File("RandomBoard4.txt")));

       Collections.shuffle(boardScanners);

        for(int r = 0; r<Graph.length; r++){
            for(int c = 0; c<Graph[r].length; c++){
                if(r%2 == 0 && c%2 == 1 || r%2== 1 && c%2 == 0){
                    Graph[r][c]= new Hex();
                }
            }
        }

        for(int r = 0; r< 20; r++){
            for(int c = 0; c<20; c++){
                if(!(r%2 == 0 && c%2 == 1 || r%2== 1 && c%2 == 0)){
                    Graph[r][c] = map.get(boardScanners.get(0).nextInt());
                }
            }
        }

        for(int r = 0; r< 20; r++){
            for(int c = 20; c<40; c++){
                if(!(r%2 == 0 && c%2 == 1 || r%2== 1 && c%2 == 0)){
                    Graph[r][c] = map.get(boardScanners.get(1).nextInt());
                }
            }
        }
        for(int r =20; r< 40; r++){
            for(int c = 0; c<20; c++){
                if(!(r%2 == 0 && c%2 == 1 || r%2== 1 && c%2 == 0)){
                    Graph[r][c] = map.get(boardScanners.get(2).nextInt());
                }
            }
        }

        for(int r =20; r< 40; r++){
            for(int c = 20; c<40; c++){
                if(!(r%2 == 0 && c%2 == 1 || r%2== 1 && c%2 == 0)){
                    Graph[r][c] = map.get(boardScanners.get(3).nextInt());
                }
            }
        }

       


      
       
       
    }

    public Hex[][] getGraph() {
        return Graph;
    }

    public void colorGraph() {
    }

    public void placeSettlement(Settlement t, Hex h) {
    }

}
