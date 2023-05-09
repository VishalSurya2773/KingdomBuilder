import java.util.TreeMap;
import java.util.zip.ZipInputStream;
import java.util.*;
import java.io.*;

public class Board {
    public static Hex[][] Graph;
    private TreeMap<Integer, String> map;
    private ArrayList<Scanner> boardScanners;
    public static int[] numbers, boards;
    private String[] boardStrings;
    private String[] randBoards;

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
        boardStrings = new String[8];
        String board1 = "1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 1 0 2 2 2 0 2 2 11 0 0 1 1 2 2 2 2 2 3 3 1 1 1 1 1 2 2 5 3 3 3 1 6 1 1 1 2 3 3 5 4 1 6 6 11 4 3 3 5 3 3 4 6 6 4 4 3 3 6 14 4 4 6 6 6 4 4 5 6 6 4 4 6 6 6 4 5 6 6 4 4 4";
        String board2 = "0 0 1 5 5 4 4 4 6 6 0 14 1 5 4 4 4 8 6 6 1 1 1 3 3 3 4 1 3 3 1 1 3 3 5 0 0 1 1 3 1 6 6 5 3 3 0 0 1 1 6 6 8 3 5 3 5 0 0 1 6 6 6 4 3 3 5 5 0 0 6 6 4 4 2 5 5 5 0 5 6 2 4 4 5 5 5 5 5 5 4 4 4 5 5 5 5 5 5 5";
        String board3 = "6 6 6 4 4 5 6 4 4 4 6 6 6 14 4 5 6 4 4 4 6 3 3 6 4 4 5 6 6 4 3 3 1 6 4 5 3 7 4 4 3 3 3 1 1 5 3 3 5 5 2 2 1 6 6 5 5 5 0 0 1 1 1 2 6 3 3 3 0 0 1 1 14 0 2 0 3 3 1 1 5 5 5 0 0 0 0 2 1 1 5 5 5 5 0 0 0 0 0 1";
        String board4 = "6 6 4 4 4 5 6 4 4 3 6 3 4 4 5 6 4 4 3 3 6 3 3 4 5 6 6 3 3 3 3 3 4 4 5 6 2 3 0 0 1 3 14 4 5 6 0 0 0 0 1 1 4 5 6 6 2 2 0 0 1 1 5 5 5 6 0 0 0 1 5 5 6 6 5 5 12 1 2 1 5 0 12 6 5 2 5 1 1 1 5 0 0 5 5 5 5 1 1 1";
        String board5 = "4 4 4 4 2 2 6 2 1 1 4 2 4 4 3 6 2 2 2 1 3 3 4 3 3 3 6 6 5 2 0 3 3 3 5 9 6 5 2 2 0 0 0 0 3 5 6 5 1 1 0 1 0 0 0 5 5 1 6 1 0 0 1 0 0 5 3 12 6 1 1 1 9 0 5 3 3 3 6 6 0 1 5 5 5 4 4 3 6 6 0 1 1 5 4 4 4 6 6 6";
        String board6 = "0 0 1 5 5 4 4 6 6 6 0 1 5 3 3 4 4 4 6 6 0 0 5 3 3 4 4 15 3 6 5 5 5 3 6 4 3 3 3 3 5 5 5 5 6 6 6 6 3 3 5 4 4 5 6 6 1 1 0 1 5 4 1 4 5 6 1 1 0 1 5 12 1 3 5 15 0 0 1 5 5 5 1 3 5 5 5 0 0 5 5 5 5 5 5 5 5 5 5 5";
        String board7 = "1 1 1 0 0 5 0 0 0 0 2 2 1 0 0 5 0 0 0 0 2 2 1 2 2 5 0 0 13 3 2 1 2 2 5 2 0 3 3 3 1 1 4 4 5 2 2 1 3 3 1 4 4 5 1 1 1 2 3 3 1 13 4 4 5 3 3 3 3 3 6 6 4 5 6 14 6 3 6 4 6 6 4 4 5 6 6 6 6 4 6 6 4 4 5 6 6 6 4 4";
        String board8 = "3 0 0 2 2 0 0 1 1 1 3 3 0 0 0 2 2 1 1 1 3 3 3 3 3 3 3 2 2 2 5 5 3 12 6 6 4 4 2 2 3 3 5 5 6 6 6 4 4 1 3 1 1 5 6 4 4 1 1 1 0 3 10 1 5 4 4 10 1 6 0 0 1 5 4 4 6 6 6 6 0 0 0 5 4 4 4 6 6 6 0 0 5 5 4 4 4 6 6 6";
        // System.out.println(board1.length());
        // System.out.println(board2.length());
        // System.out.println(board3.length());
        // System.out.println(board4.length());
        // System.out.println(board5.length());
        // System.out.println(board6.length());
        // System.out.println(board7.length());
        // System.out.println(board8.length());

        boardStrings[0] = board1;
        boardStrings[1] = board2;
        boardStrings[2] = board3;
        boardStrings[3] = board4;
        boardStrings[4] = board5;
        boardStrings[5] = board6;
        boardStrings[6] = board7;
        boardStrings[7] = board8;
    }

    public String[] getRandBoards() {
        return randBoards;
    }

    public void makeGraph() throws IOException {
        randBoards = new String[4];
        for (int i = 0; i < randBoards.length; i++) {
            boolean added = false;
            while (!added) {
                int r = (int) (Math.random() * 8);
                boolean contains = false;
                for (String j : randBoards) {
                    if (boardStrings[r].equals(j)) {
                        contains = true;
                    }
                }
                if (!contains) {
                    randBoards[i] = boardStrings[r];
                    numbers[i] = r;
                    added = true;
                }
            }
        }
        // System.out.print("Boards: ");
        // for (String i : randBoards) {
        // System.out.print(i);
        // }
        // System.out.println();
        // boards = randBoards;
        // try {
        // boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[0]
        // + ".txt")));
        // boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[1]
        // + ".txt")));
        // boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[2]
        // + ".txt")));
        // boardScanners.add(new Scanner(new File("src/text_files/Board" + randBoards[3]
        // + ".txt")));
        // } catch (IOException e) {
        // System.out.println("Scanner reading failure");
        // }

        // numbers = randBoards;
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

        // Scanner a = boardScanners.get(0);
        int inputHexes = 0;
        int idx = 0;
        int count = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (count < 100) {
                        String num = "";
                        if (idx + 1 < randBoards[0].length() && randBoards[0].charAt(idx + 1) != ' ') {
                            num = randBoards[0].substring(idx, idx + 2);
                            idx++;
                        } else
                            num = randBoards[0].substring(idx, idx + 1);
                        idx += 2;
                        count++;
                        Graph[r][c] = new Hex(map.get(Integer.parseInt(num)));
                        // if (Integer.parseInt(num) >= 7) {
                        // Graph[r][c] = new SpecialHex(map.get(Integer.parseInt(num)));
                        // } else {

                        // }

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
        // System.out.println("Sector 1 " + inputHexes);
        coordY = 151;
        coordX = 562 + 10 * changeX;
        inputHexes = 0;
        idx = 0;
        count = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (count < 100) {
                        String num = "";
                        if (idx + 1 < randBoards[1].length() && randBoards[1].charAt(idx + 1) != ' ') {
                            num = randBoards[1].substring(idx, idx + 2);
                            idx++;
                        } else
                            num = randBoards[1].substring(idx, idx + 1);
                        idx += 2;
                        count++;
                        Graph[r][c] = new Hex(map.get(Integer.parseInt(num)));
                        // if (Integer.parseInt(num) >= 7) {
                        // Graph[r][c] = new SpecialHex(map.get(Integer.parseInt(num)));
                        // } else {

                        // }
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
        // System.out.println("Sector 2 " + inputHexes);

        idx = 0;
        count = 0;
        inputHexes = 0;
        int scoordY = coordY;
        coordX = 562;
        scoordX = 582;
        for (int r = 10; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    if (count < 100) {
                        String num = "";
                        if (idx + 1 < randBoards[2].length() && randBoards[2].charAt(idx + 1) != ' ') {
                            num = randBoards[2].substring(idx, idx + 2);
                            idx++;
                        } else
                            num = randBoards[2].substring(idx, idx + 1);
                        idx += 2;
                        count++;
                        Graph[r][c] = new Hex(map.get(Integer.parseInt(num)));
                        // if (Integer.parseInt(num) >= 7) {
                        // Graph[r][c] = new SpecialHex(map.get(Integer.parseInt(num)));
                        // } else {

                        // }
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
        // System.out.println(coordX + " " + coordY);
        // System.out.println("Sector 3 " + inputHexes);
        idx = 0;
        count = 0;
        inputHexes = 0;
        scoordY = coordY;
        coordX = 562 + 10 * changeX;
        for (int r = 10; r < 20; r++) {
            for (int c = 20; c < 40; c++) {
                if (!((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))) {
                    // System.out.println(a.hasNextInt());
                    if (count < 100) {
                        String num = "";
                        if (idx + 1 < randBoards[3].length() && randBoards[3].charAt(idx + 1) != ' ') {
                            num = randBoards[3].substring(idx, idx + 2);
                            idx++;
                        } else
                            num = randBoards[3].substring(idx, idx + 1);
                        idx += 2;
                        count++;
                        Graph[r][c] = new Hex(map.get(Integer.parseInt(num)));
                        // if (Integer.parseInt(num) >= 7) {
                        // Graph[r][c] = new SpecialHex(map.get(Integer.parseInt(num)));
                        // } else {

                        // }
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
        // System.out.println("Sector 4 " + inputHexes);
        // for (int r = 0; r < 10; r++) {
        // for (int c = 0; c < 20; c++) {
        // if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
        // Graph[r][c].setSettlement(new Settlement("Red", this)); // basically just a
        // null pointer
        // }
        // }
        // }
        // for (int r = 0; r < 10; r++) {
        // for (int c = 20; c < 40; c++) {
        // if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
        // Graph[r][c].setSettlement(new Settlement("Blue", this)); // basically just a
        // null pointer
        // }
        // }
        // }
        // for (int r = 10; r < 20; r++) {
        // for (int c = 0; c < 20; c++) {
        // if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
        // Graph[r][c].setSettlement(new Settlement("Green", this)); // basically just a
        // null pointer
        // }
        // }
        // }
        // for (int r = 10; r < 20; r++) {
        // for (int c = 20; c < 40; c++) {
        // if (!((r % 2 == 0 && c % 2 == 1) && (r % 2 == 1 && c % 2 == 0))) {
        // Graph[r][c].setSettlement(new Settlement("Purple", this)); // basically just
        // a null pointer
        // }
        // }
        // }
        // printGraph();
        // for (int r = 0; r < 20; r++) {
        // for (int c = 0; c < 40; c++) {
        // if (Graph[r][c].getTerrain() == null)
        // System.out.println("NULL AT " + r + " " + c);
        // if (!Graph[r][c].getTerrain().equals("blank")) {
        // Graph[r][c].setAdjacent(r, c, Graph);
        // Graph[r][c].setAvail(true);
        // // Graph[r][c].printAdjacent();
        // }
        // }
        // }
    }

    public Hex[][] getGraph() {
        return Graph;
    }

    public void printGraph() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                if (Graph[i][j].getTerrain() == null) {
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
