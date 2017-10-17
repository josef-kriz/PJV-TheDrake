package ovoce.thedrake;

public enum TroopFace {
    FRONT, BACK;

    public static TroopFace flip(TroopFace face) {
        if (face == TroopFace.FRONT) return TroopFace.BACK;
        else return TroopFace.FRONT;
    }
}
