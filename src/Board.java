import java.util.TreeMap;
import java.util.*;
import java.io.*;

public class Board {
    private Hex[][] Graph;
    private TreeMap<String, Hex> map;

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
    }

    public void makeGraph() {

    }

    public Hex[][] getGraph() {
        return Graph;
    }

    public void colorGraph() {
    }

    public void placeSettlement(Settlement t, Hex h) {
    }

}
