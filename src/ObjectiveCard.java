public class ObjectiveCard {

    private String type;

    public ObjectiveCard(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

    public int getScore(String settlementColor) {
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

    public int citizen(Player p) {
        int numofadajancies =  p.findAdjacentSettlements(p.getColor()) / 2;
        int points = 1 * numofadajancies;
        return points;
    }

    public int discoverer(String settlementColor) {
        for(int r = 0; r<40; r++) {
            for(int c = 0; c<40; c++) {

            }
        }
    }

    public int farmer(String settlementColor) {
        return 0;
    }

    public int fisherman(String settlementColor) {
        return 0;
    }

    public int hermit(String settlementColor) {
        return 0;
    }

    public int knight(String settlementColor) {
        return 0;
    }

    public int lord(String settlementColor) {
        return 0;
    }

    public int merchant(String settlementColor) {
        return 0;
    }

    public int miner(String settlementColor) {
        return 0;
    }

    public int worker(String settlementColor) {
        return 0;
    }
}
