import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;
import java.util.Collections;
import java.util.Random;

public class Board {
    public static Hex[][] Graph;
    private TreeMap<Integer, Hex> map;
    private ArrayList<Scanner> boardScanners;
    public static int[] numbers;

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
        try {
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[0] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[1] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[2] + ".txt")));
            boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[3] + ".txt")));
        } catch (IOException e) {
            System.out.println("Scanner reading failure");
        }

        numbers = randBoards;
        int coordX = 664;
        int coordY = 292;

        int scoordX = 680; // for odd rows

        int changeX = 30;
        int changeY = 26;

        for (int r = 0; r < Graph.length; r++) {
            for (int c = 0; c < Graph[r].length; c++) {
                if ((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0)) {
                    Graph[r][c] = new Hex("blank"); // basically just a null pointer
                    Graph[r][c].setAdjacent();
                    Graph[r][c].setX(coordX);
                    Graph[r][c].setY(coordY);
                    coordX += changeX;
                }
            }
            coordY += changeY;
        }
        coordX = 664;
        for (int r = 0; r < 20; r++) {
            if (boardScanners.get(0).hasNextLine()) {
                Scanner a = new Scanner(boardScanners.get(0).nextLine());
                for (int c = 0; c < 20; c++) {
                    if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                        if (a.hasNextInt()) {
                            Graph[r][c] = map.get(a.nextInt());
                            Graph[r][c].setAdjacent();
                            Graph[r][c].setX(coordX);
                            Graph[r][c].setY(coordY);
                            scoordX += changeX;
                        }
                    }
                }

            }
            coordY += changeY;
        }
        scoordX = 680;

        for (int r = 0; r < 20; r++) {
            if (boardScanners.get(1).hasNextLine()) {
                Scanner a = new Scanner(boardScanners.get(1).nextLine());
                for (int c = 20; c < 40; c++) {
                    if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                        if (a.hasNextInt()) {
                            Graph[r][c] = map.get(a.nextInt());
                            Graph[r][c].setAdjacent();
                            Graph[r][c].setX(coordX);
                            Graph[r][c].setY(coordY);
                            coordX += changeX;
                        }
                    }
                }
            }
            coordY += changeY;
        }
        coordX = 664;
        for (int r = 20; r < 40; r++) {
            if (boardScanners.get(2).hasNextLine()) {
                Scanner a = new Scanner(boardScanners.get(2).nextLine());
                for (int c = 0; c < 20; c++) {
                    if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                        if (a.hasNextInt()) {
                            Graph[r][c] = map.get(a.nextInt());
                            Graph[r][c].setAdjacent();
                            Graph[r][c].setX(coordX);
                            Graph[r][c].setY(coordY);
                            scoordX += changeX;
                        }
                    }
                }
            }
            coordY += changeY;
        }
        scoordX = 680;
        for (int r = 20; r < 40; r++) {
            if (boardScanners.get(3).hasNextLine()) {
                Scanner a = new Scanner(boardScanners.get(3).nextLine());
                for (int c = 20; c < 40; c++) {
                    if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                        if (a.hasNextInt()) {
                            Graph[r][c] = map.get(a.nextInt());
                            Graph[r][c].setAdjacent();
                            Graph[r][c].setX(coordX);
                            Graph[r][c].setY(coordY);
                            coordX += changeX;
                        }
                    }
                }
            }
            coordY += changeY;
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

    public static int[] getNumbers() {
        return numbers;
    }
}
