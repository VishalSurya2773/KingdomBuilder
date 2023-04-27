import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class ObjectiveCard {
    
    private String type;
    private Player p;
    private BufferedImage cardimage;
    private int merchantScore = 0;

    public ObjectiveCard(String t) {
        type = t;
    }

    public ObjectiveCard(BufferedImage b) {
        cardimage = b;
    }

    public String getType() {
        return type;
    }

    public BufferedImage getImage(String type) throws IOException {
        if (type.equals("citizen")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Citizens.png"));
        } else if (type.equals("discoverer")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Discoverers.png"));
        } else if (type.equals("farmer")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Farmers.png"));
        } else if (type.equals("fisherman")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Fishermen.png"));
        } else if (type.equals("hermit")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Hermits.png"));
        } else if (type.equals("knight")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Knights.png"));
        } else if (type.equals("lord")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Lords.png"));
        } else if (type.equals("merchant")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Merchants.png"));
        } else if (type.equals("miner")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Miners.png"));
        } else if (type.equals("worker")) {
            cardimage = ImageIO.read(ObjectiveCard.class.getResource("images/Obj_Workers.png"));
        }
        return cardimage;
    }

    public int getScore(String settlementColor) {
        if (settlementColor.equals(Game.players.get(0).getColor())) {
            p = Game.players.get(0);
        } else if (settlementColor.equals(Game.players.get(1).getColor())) {
            p = Game.players.get(1);
        } else if (settlementColor.equals(Game.players.get(2).getColor())) {
            p = Game.players.get(2);
        } else if (settlementColor.equals(Game.players.get(3).getColor())) {
            p = Game.players.get(3);
        }
        if (type.equals("citizen")) {
            return citizen(settlementColor);
        } else if (type.equals("discoverer")) {
            return discoverer(settlementColor);
        } else if (type.equals("farmer")) {
            return farmer(settlementColor);
        } else if (type.equals("fisherman")) {
            return fisherman(settlementColor);
        } else if (type.equals("hermit")) {
            return hermit(settlementColor);
        } else if (type.equals("knight")) {
            return knight(settlementColor);
        } else if (type.equals("lord")) {
            return lord(settlementColor);
        } else if (type.equals("merchant")) {
            return merchant(settlementColor);
        } else if (type.equals("miner")) {
            return miner(settlementColor);
        } else if (type.equals("worker")) {
            return worker(settlementColor);
        }
        return 0;
    }

    public int findNumSettlementsInSector(String settlementColor, int sect) {
        int length = 0;
        int width = 0;
        if (sect == 1) {
            length = 20;
            width = 20;
        } else if (sect == 2) {
            length = 40;
            width = 40;
        } else if (sect == 3) {
            length = 20;
            width = 40;
        } else if (sect == 4) {
            length = 40;
            width = 20;
        }
        int total = 0;
        for (int r = length - 20; r < length; r++) {
            for (int c = width - 20; c < width; c++) {
                if (Board.getGraph()[r][c].getSettlement().getColor().equals(settlementColor)) {
                    total++;
                }
            }
        }
        return total;
    }

    public int citizen(String settlementColor) {
        int s = 0;
        int temp = 0;
        int max = 0;
        for (int r = 0; r < 40; r++) {
            for (int c = 0; c < 40; c++) {
                if (Board.getGraph()[r][c].getSettlement().equals(settlementColor)) {
                    Board.getGraph()[r][c].getSettlement().findAdjacents();
                    Settlement[] adjs = Board.getGraph()[r][c].getSettlement().adjacents();
                    temp = adjs.length;
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        s = max / 2 * 1;
        return s;
    }

    public int discoverer(String settlementColor) {
        int s = 0;
        for (int r = 0; r < 40; r++) {
            for (int c = 0; c < 40; c++) {
                if (Board.getGraph()[r][c] != null) {
                    if (Board.getGraph()[r][c].getSettlement().getColor().equals(settlementColor)) {
                        s++;
                        r++;
                    }
                }
            }
        }
        return s;
    }

    public int farmer(String settlementColor) {
        int s1 = findNumSettlementsInSector(settlementColor, 1);
        int s2 = findNumSettlementsInSector(settlementColor, 2);
        int s3 = findNumSettlementsInSector(settlementColor, 3);
        int s4 = findNumSettlementsInSector(settlementColor, 4);
        if (s1 < s2 && s1 < s3 && s1 < s4) {
            return s1 * 3;
        } else if (s2 < s1 && s2 < s3 && s2 < s4) {
            return s2 * 3;
        } else if (s3 < s1 && s3 < s2 && s3 < s4) {
            return s3 * 3;
        } else if (s4 < s1 && s4 < s2 && s4 < s3) {
            return s4 * 3;
        }
        return 0;
    }

    public int fisherman(String settlementColor) {
        int total = 0;
        Hex[][] b = Board.getGraph();
        for (int r = 0; r < b.length; r++) {
            for (int c = 0; c < b[r].length; c++) {
                if (b[r][c].getSettlement().getColor().equals(settlementColor) && isAdjToWater(b[r][c].adjacents())) {
                    total++;
                }
            }
        }
        return total;

    }

    public boolean isAdjToWater(Hex[] adj) {
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getTerrain().equals("water")) {
                return true;
            }
        }
        return false;
    }

    public int hermit(String settlementColor) {
        int s = 0;
        for (int r = 0; r < 40; r++) {
            for (int c = 0; c < 40; c++) {
                if (Board.getGraph()[r][c] != null) {
                    if (Board.getGraph()[r][c].getSettlement().getColor().equals(settlementColor)) {
                        s++;
                    }
                }
            }
        }
        return s;
    }

    public int knight(String settlementColor) {
        int temp = 0;
        int largest = 0;
        for (int r = 0; r < Board.getGraph().length; r++) {
            for (int c = 0; c < Board.getGraph()[r].length; c++) {
                if (Board.getGraph()[r][c].getSettlement().getColor().equals(settlementColor))
                    temp++;
            }
            if (temp > largest) {
                largest = temp;
            }
        }
        return largest * 2;
    }

    public int lord(String settlementColor) {
        int pNum = 0;
        ArrayList<Player> ps = Game.players;
        if (settlementColor.equals(ps.get(0).getColor())) {
            pNum = ps.get(0).playerNum;
        } else if (settlementColor.equals(ps.get(1).getColor())) {
            pNum = ps.get(1).playerNum;
        } else if (settlementColor.equals(ps.get(2).getColor())) {
            pNum = ps.get(2).playerNum;
        } else if (settlementColor.equals(ps.get(3).getColor())) {
            pNum = ps.get(3).playerNum;
        }

        if (lordRankings().get(0).equals("p" + pNum + 1 + "")) {
            return 12;
        } else if (lordRankings().get(1).equals("p" + pNum + 1 + "")) {
            return 6;
        }
        return 0;
    }

    public int getLargestNumSetInSect(String settlementColor) {
        int[] scores = { findNumSettlementsInSector(settlementColor, 1), findNumSettlementsInSector(settlementColor, 2),
                findNumSettlementsInSector(settlementColor, 3), findNumSettlementsInSector(settlementColor, 4) };
        int largest = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > largest) {
                largest = scores[i];
            }
        }
        return largest;
    }

    public ArrayList<Player> lordRankings() { // highest gets 12, second highest gets 6
        ArrayList<Player> ps = Game.players;
        int p1 = getLargestNumSetInSect(ps.get(0).getColor());
        int p2 = getLargestNumSetInSect(ps.get(1).getColor());
        int p3 = getLargestNumSetInSect(ps.get(2).getColor());
        int p4 = getLargestNumSetInSect(ps.get(3).getColor());
        Map<Integer, Player> rankings = new HashMap<Integer, Player>();

        rankings.put(1, ps.get(0));
        rankings.put(2, ps.get(1));
        rankings.put(3, ps.get(2));
        rankings.put(4, ps.get(3));
        TreeMap<Integer, Player> sorted = new TreeMap<>();
        sorted.putAll(rankings);
        return (ArrayList) sorted.keySet();
    }

    public void floodfill(Hex h, Player p, HashMap<Hex, Boolean> map) {
        if (h.getSettlement() == null)
            return;
        if (h.getSettlement().getPlayer() != p)
            return;
        if (map.get(h))
            return;
        merchantScore++;
        map.put(h, true);
        Hex[] arr = h.adjacents();
        for (int i = 0; i < arr.length; i++) {
            floodfill(arr[i], p, map);
        }

    }

    public int merchant(String settllementColor) {

        int ans = 0;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (((SpecialHex) Board.getGraph()[i][j]).isCastle()) {
                    HashMap<Hex, Boolean> map = new HashMap<Hex, Boolean>();
                    floodfill(Board.getGraph()[i][j], p, map);
                    ans = Math.max(ans, merchantScore);
                    merchantScore = 0;
                }
            }
        }
        return ans;
    }

    public int miner(String settlementColor) {
        int total = 0;
        Hex[][] b = Board.getGraph();
        for (int r = 0; r < b.length; r++) {
            for (int c = 0; c < b[r].length; c++) {
                if (b[r][c].getSettlement().getColor().equals(settlementColor) && isAdjToWater(b[r][c].adjacents())) {
                    total++;
                }
            }
        }
        return total;
    }

    public boolean isAdjToMountain(Hex[] adj) {
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].getTerrain().equals("water")) {
                return true;
            }
        }
        return false;
    }

    public int worker(String settlementColor) {
        int total = 0;
        for (int r = 0; r < 40; r++) {
            for (int c = 0; c < 40; c++) {
                if (Board.getGraph()[r][c].getSettlement().getColor().equals(settlementColor)) {
                    Hex[] adjs = Board.getGraph()[r][c].adjacents();
                    for (Hex h : adjs) {
                        if (h.getTerrain().equals("castle")) {
                            total++;
                        }
                    }
                }
            }
        }
        return total;
    }
}
