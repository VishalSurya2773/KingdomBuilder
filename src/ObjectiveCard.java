public class ObjectiveCard {

    private String type;

    public ObjectiveCard(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

    public int getScore(String settlementColor) {
        if (type == "citizen") {
            return citizen(settlementColor);
        } else if (type == "discoverer") {
            return discoverer(settlementColor);
        } else if (type == "farmer") {
            return farmer(settlementColor);
        } else if (type == "fisherman") {
            return fisherman(settlementColor);
        } else if (type == "hermit") {
            return hermit(settlementColor);
        } else if (type == "knight") {
            return knight(settlementColor);
        } else if (type == "lord") {
            return lord(settlementColor);
        } else if (type == "merchant") {
            return merchant(settlementColor);
        } else if (type == "miner") {
            return miner(settlementColor);
        } else if (type == "worker") {
            return worker(settlementColor);
        }
        return 0;
    }

    public int citizen(String settlementColor) {
        // find largest cluster of adjacent settlements
        // add 2 points for

        return 0;
    }

    public int discoverer(String settlementColor) {
        return 0;
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
