//výčtový typ představující rubovou nebo lícovou stranu jednotky
package ovoce.thedrake;

public enum TroopFace {
    FRONT, BACK {
        @Override
        public TroopFace flipped() {
            return FRONT;
        }
    };

    public TroopFace flipped() {
        return BACK;
    }

    public static TroopFace flip(TroopFace face) {
        if (face == TroopFace.FRONT) return TroopFace.BACK;
        else return TroopFace.FRONT;
    }
}