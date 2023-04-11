public class ObjectiveCard {

    private String type;
    private Player p;

    public ObjectiveCard(String t) {
        type = t;
    }

    public String getType() {
        return type;
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

    public int citizen(String settlementColor) {
        return 0;
    }

    public int discoverer(String settlementColor) {
        for (int r = 0; r < 40; r++) {
            for (int c = 0; c < 40; c++) {

            }
        }
        return -5;
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
