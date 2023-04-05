import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;

public class Board {
    private Hex[][] Graph;
    private TreeMap<Integer, Hex> map;
    private ArrayList<Scanner> boardScanners;

    public Board() {
        Graph = new Hex[40][40]; // tentative values
        map = new TreeMap<Integer, Hex>();
        map.put(0, new Hex("sand"));
        map.put(1, new Hex("canyon"));
        map.put(2, new Hex("mountain"));
        map.put(3, new Hex("flower"));
        map.put(4, new Hex("forest"));
        map.put(5, new Hex("water"));
        map.put(6, new Hex("grass"));
        map.put(7, new SpecialHex("oracle"));
        map.put(8, new SpecialHex("farm"));
        map.put(9, new SpecialHex("tower"));
        map.put(10, new SpecialHex("tavern"));
        map.put(11, new SpecialHex("barn"));
        map.set(12, new Specialhex("harbor"));
        map.put(13, new SpecialHex("paddock"));
        map.put(14, new SpecialHex("castle"));
        map.put(15, new SpecialHex("oasis"));
        boardScanners = new ArrayList<Scanner>();
    }

    public void makeGraph() throws IOException {
        boardScanners.add(new Scanner(new File("RandomBoard1.txt")));
        boardScanners.add(new Scanner(new File("RandomBoard2.txt")));
        boardScanners.add(new Scanner(new File("RandomBoard3.txt")));
        boardScanners.add(new Scanner(new File("RandomBoard4.txt")));

        Collections.shuffle(boardScanners);

        for (int r = 0; r < Graph.length; r++) {
            for (int c = 0; c < Graph[r].length; c++) {
                if (r % 2 == 0 && c % 2 == 1 || r % 2 == 1 && c % 2 == 0) {
                    Graph[r][c] = new Hex();
                }
            }
        }

        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!(r % 2 == 0 && c % 2 == 1 || r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = map.get(boardScanners.get(0).nextInt());
                }
            }
        }

        for (int r = 0; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!(r % 2 == 0 && c % 2 == 1 || r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = map.get(boardScanners.get(1).nextInt());
                }
            }
        }
        for (int r = 20; r < 40; r++) {
            for (int c = 0; c < 20; c++) {
                if (!(r % 2 == 0 && c % 2 == 1 || r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = map.get(boardScanners.get(2).nextInt());
                }
            }
        }

        for (int r = 20; r < 40; r++) {
            for (int c = 20; c < 40; c++) {
                if (!(r % 2 == 0 && c % 2 == 1 || r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = map.get(boardScanners.get(3).nextInt());
                }
            }
        }

    }

    public Hex[][] getGraph() {
        return Graph;
    }

    public void colorGraph() {
        // low key this thing doesnt do anything
    }

    public void placeSettlement(Settlement t, Hex h) {
        // I don't think we need this here, it makes more sense for this to be in the
        // hex class and in the player class where the person iterates through the
        // board from the player class and then calls it from the hex class directly

    }

}
