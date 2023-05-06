import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;

public class Board {
    public static Hex[][] Graph;
    private TreeMap<Integer, String> map;
    private ArrayList<Scanner> boardScanners;
    public static int[] numbers, boards;

    public Board() {
        Graph = new Hex[20][40];
        map = new TreeMap<Integer, String>();
        map.put(0, ("desert"));
        map.put(1, ("canyon"));
        map.put(2, ("mountain"));
        map.put(3, ("flower"));
        map.put(4, ("forest"));
        map.put(5, ("water"));
        map.put(6, ("grass"));
        map.put(7, ("oracle"));
        map.put(8, ("farm"));
        map.put(9, ("tower"));
        map.put(10, ("tavern"));
        map.put(11, ("barn"));
        map.put(12, ("harbor"));
        map.put(13, ("paddock"));
        map.put(14, ("castle"));
        map.put(15, ("oasis"));
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
        int coordX = 562;
        int coordY = 151;

        int scoordX = 582; // for odd rows

        int changeX = 41;
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
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        int num = a.nextInt();
                        if (num >= 7) {
                            Graph[r][c] = new SpecialHex(map.get(num));
                        } else {
                            Graph[r][c] = new Hex(map.get(num));
                        }

                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(coordY);
                        Graph[r][c].setArray();
                        coordX += changeX;
                        inputHexes++;
                    } else {
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            if (r % 2 == 0) {
                coordX = 582;
            } else {
                coordX = 562;
            }
            coordY += changeY;
        }
        System.out.println("Sector 1 " + inputHexes);
        coordY = 151;
        coordX = 562 + 10 * changeX;
        inputHexes = 0;
        a = boardScanners.get(1);
        for (int r = 0; r < 10; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        int num = a.nextInt();
                        if (num >= 7) {
                            Graph[r][c] = new SpecialHex(map.get(num));
                        } else {
                            Graph[r][c] = new Hex(map.get(num));
                        }
                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(coordY);
                        Graph[r][c].setArray();
                        coordX += changeX;
                        inputHexes++;
                    } else {
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            if (r % 2 == 0) {
                coordX = 582 + 10 * changeX;
            } else {
                coordX = 562 + 10 * changeX;
            }
            coordY += changeY;
        }
        System.out.println("Sector 2 " + inputHexes);

        a = boardScanners.get(2);
        inputHexes = 0;
        int scoordY = coordY;
        coordX = 562;
        scoordX = 582;
        for (int r = 10; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (a.hasNextInt()) {
                        int num = a.nextInt();
                        if (num >= 7) {
                            Graph[r][c] = new SpecialHex(map.get(num));
                        } else {
                            Graph[r][c] = new Hex(map.get(num));
                        }
                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(scoordY);
                        Graph[r][c].setArray();
                        coordX += changeX;
                        inputHexes++;
                    } else {
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            if (r % 2 == 0) {
                coordX = 582;
            } else {
                coordX = 562;
            }
            scoordY += (changeY);
        }
        System.out.println(coordX + " " + coordY);
        System.out.println("Sector 3 " + inputHexes);
        a = boardScanners.get(3);
        inputHexes = 0;
        scoordY = coordY;
        coordX = 562 + 10 * changeX;
        for (int r = 10; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    // System.out.println(a.hasNextInt());
                    if (a.hasNextInt()) {
                        int num = a.nextInt();
                        if (num >= 7) {
                            Graph[r][c] = new SpecialHex(map.get(num));
                        } else {
                            Graph[r][c] = new Hex(map.get(num));
                        }
                        Graph[r][c].setX(coordX);
                        Graph[r][c].setY(scoordY);
                        Graph[r][c].setArray();
                        coordX += changeX;
                        inputHexes++;
                    } else {
                        Graph[r][c] = new Hex("blank");
                    }
                }
            }
            if (r % 2 == 0) {
                coordX = 582 + 10 * changeX;
            } else {
                coordX = 562 + 10 * changeX;
            }
            // coordY += changeY;
            scoordY += (changeY);
            // scoordX = 582;
        }
        System.out.println("Sector 4 " + inputHexes);
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c].setSettlement(new Settlement("Red", this)); // basically just a null pointer
                }
            }
        }
        for (int r = 0; r < 10; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c].setSettlement(new Settlement("Blue", this)); // basically just a null pointer
                }
            }
        }
        for (int r = 10; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c].setSettlement(new Settlement("Green", this)); // basically just a null pointer
                }
            }
        }
        for (int r = 10; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
                    Graph[r][c].setSettlement(new Settlement("Purple", this)); // basically just a null pointer
                }
            }
        }
        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 40; c++){
                if(Graph[r][c].getTerrain().equals("blank")){
                    Graph[r][c].setAdjacent(Graph);
                }
            }
        }
    }

    public Hex[][] getGraph() {
        return Graph;
    }

    public void printGraph() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                if (Graph[i][j] == null) {
                    System.out.print("N "); // null
                } else if (Graph[i][j].getTerrain().equals("blank")) {
                    System.out.print("B "); // blank
                } else {
                    System.out.print("G "); // good
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
