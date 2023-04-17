import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;
import java.util.Collections;

public class Board {
    public static Hex[][] Graph;
    private TreeMap<Integer, Hex> map;
    private ArrayList<Scanner> boardScanners;
    public int[] numbers;

    public Board() {
        Graph = new Hex[40][40];
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
        map.put(12, new SpecialHex("harbor"));
        map.put(13, new SpecialHex("paddock"));
        map.put(14, new SpecialHex("castle"));
        map.put(15, new SpecialHex("oasis"));
        boardScanners = new ArrayList<Scanner>();
        numbers = new int[4];
        System.out.println("board creation works");
    }

    public void makeGraph() throws IOException {
        System.out.println("graph creation test");
        boardScanners.add(new Scanner(new File("text_files/RandomBoard1.txt")));
        boardScanners.add(new Scanner(new File("text_files/RandomBoard2.txt")));
        boardScanners.add(new Scanner(new File("text_files/RandomBoard3.txt")));
        boardScanners.add(new Scanner(new File("text_files/RandomBoard7.txt")));
        System.out.println("before shuffle");
        Collections.shuffle(boardScanners);
        System.out.println("after shuffle");
        System.out.println(boardScanners.get(0).nextInt());

        numbers[0] = boardScanners.get(0).nextInt();
        numbers[1] = boardScanners.get(1).nextInt();
        numbers[2] = boardScanners.get(2).nextInt();
        numbers[3] = boardScanners.get(3).nextInt();

        for (int r = 0; r < Graph.length; r++) {
            for (int c = 0; c < Graph[r].length; c++) {
                if ((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = new Hex(""); // basically just a null pointer
                    Graph[r][c].setAdjacent();
                }
            }
        }

        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c] = map.get(boardScanners.get(0).nextInt());
                    Graph[r][c].setAdjacent();
                }
            }
        }

        for (int r = 0; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c] = map.get(boardScanners.get(1).nextInt());
                    Graph[r][c].setAdjacent();
                }
            }
        }
        for (int r = 20; r < 40; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c] = map.get(boardScanners.get(2).nextInt());
                    Graph[r][c].setAdjacent();
                }
            }
        }

        for (int r = 20; r < 40; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c] = map.get(boardScanners.get(3).nextInt());
                    Graph[r][c].setAdjacent();
                }
            }
        }

    }

    public static Hex[][] getGraph() {
        return Graph;
    }
    
    public boolean isValid(int r, int c) {
        if (r >= 0 && r < Graph.length && c >= 0 && c <= Graph[0].length) {
            return !Graph[r][c].getTerrain().equals("");
        }
        return false;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
