//třída představuje jednotku ve hře
package ovoce.thedrake;

public class Troop {
    private final TroopInfo info;
    private final PlayingSide side;
    private final TroopFace face;

    public Troop(TroopInfo info, PlayingSide side, TroopFace face) {
        this.info = info;
        this.side = side;
        this.face = face;
    }

    public TroopInfo info() {
        return this.info;
    }

    public PlayingSide side() {
        return this.side;
    }

    public TroopFace face() {
        return this.face;
    }

    public Offset2D pivot() {
        return this.info.pivot(this.face);
    }

    public Troop flipped() {
        return new Troop(this.info, this.side, TroopFace.flip(this.face));
    }
}
