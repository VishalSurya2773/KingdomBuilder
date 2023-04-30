import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;

public class Board {
    public static Hex[][] Graph;
    private TreeMap<Integer, Hex> map;
    private ArrayList<Scanner> boardScanners;
    public static int[] numbers, boards;

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
    }

    public void makeGraph() throws IOException {
        int[] randBoards = new int[4];
        for (int i = 0; i < randBoards.length; i++) {
            boolean added = false;
            while (!added) {
                int r = (int) (Math.random() * 8) + 1;
                boolean contains = false;
                for (int j : randBoards) {
                    if (r == j) {
                        contains = true;
                    }
                }
                if (!contains) {
                    randBoards[i] = r;
                    added = true;
                }
            }
        }
        System.out.print("Boards: ");
        for (int i : randBoards) {
            System.out.print(i);
        }
        System.out.println();
        boards = randBoards;
        try {
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[0] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[1] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[2] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[3] + ".txt")));
        } catch (IOException e) {
            System.out.println("Scanner reading failure");
        }

        numbers = randBoards;
        int coordX = 567;
        int coordY = 149;

        int scoordX = 587; // for odd rows

        int changeX = 40;
        int changeY = 35;

        for (int r = 0; r < Graph.length; r++) {
            for (int c = 0; c < Graph[r].length; c++) {
                if ((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = new Hex("blank"); // basically just a null pointer
                }
            }
        }

        Scanner a = boardScanners.get(0);
        int inputHexes = 0;
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        Graph[r][c] = map.get(a.nextInt());
                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(coordY);
                        coordX += changeX;
                        inputHexes++;
                    }
                    else{
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            coordY += changeY;
        }
        System.out.println("Sector 1 " + inputHexes);
        coordX = 567;
        inputHexes = 0;
        a = boardScanners.get(1);
        for (int r = 0; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        Graph[r][c] = map.get(a.nextInt());
                        Graph[r][c].setX(scoordX);
                        Graph[r][c].setY(coordY);
                        scoordX += changeX;
                        inputHexes++;
                    }
                    else{
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            coordY += changeY;
        }
        System.out.println("Sector 2 " + inputHexes);
        scoordX = 587;

        a = boardScanners.get(2);
        inputHexes = 0;
        for (int r = 20; r < 40; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        Graph[r][c] = map.get(a.nextInt());
                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(coordY);
                        coordX += changeX;
                        inputHexes++;
                    }
                    else{
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            coordY += changeY;
        }
        System.out.println("Sector 3 " + inputHexes);
        coordX = 567;
        a = boardScanners.get(3);
        inputHexes = 0;
        for (int r = 20; r < 40; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    // System.out.println(a.hasNextInt());  
                    if (a.hasNextInt()) {
                        Graph[r][c] = map.get(a.nextInt());
                        Graph[r][c].setX(scoordX);
                        Graph[r][c].setY(coordY);
                        scoordX += changeX;
                        inputHexes++;
                    }
                    else{
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            coordY += changeY;
        }
        System.out.println("Sector 4 " + inputHexes);
        int nulls = 0;
        int blanks = 0;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                Graph[i][j].setAdjacent();
                if (Graph[i][j] == null) {
                    System.out.println(i + ", " + j + ": N");
                    nulls++;
                    
                } else if (Graph[i][j].getTerrain().equals("blank")) {
                    blanks++;
                }
            }
        }
        // printGraph();
        System.out.println("Null " + nulls);
        System.out.println("Blanks " + blanks);
    }

    public static Hex[][] getGraph() {
        return Graph;
    }

    public void printGraph() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if(Graph[i][j] == null){
                    System.out.print("null ");
                }
                else if (Graph[i][j].getTerrain().equals("blank")) {
                    System.out.print("blank ");
                } else {
                    System.out.print(Graph[i][j].getTerrain() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValid(int r, int c) {
        if (r >= 0 && r < Graph.length && c >= 0 && c <= Graph[0].length) {
            return !Graph[r][c].getTerrain().equals("blank");
        }
        return false;
    }

    public static int[] getNumbers() {
        return numbers;
    }

    public int[] getBoards() {
        return boards;
    }
}
