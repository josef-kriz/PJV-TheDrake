//třída představuje jednotku ve hře
package ovoce.thedrake;

import java.util.ArrayList;
import java.util.List;

public class Troop {
    private final TroopInfo info;
    private final PlayingSide side;
    private final TroopFace face;

    public Troop(TroopInfo info, PlayingSide side, TroopFace face) {
        this.info = info;
        this.side = side;
        this.face = face;
    }
    // Vytvoří jednotku lícem nahoru
    public Troop(TroopInfo info, PlayingSide side) {
        this.info = info;
        this.side = side;
        this.face = TroopFace.FRONT;
    }

    // Všechny změny desky, které může jednotka provést na desce board, pokud stojí na pozici pos.
    public List<BoardChange> changesFrom(TilePosition pos, Board board) {
        List<BoardChange> changes= new ArrayList<>();
        for(TroopAction action: info.actions(face))
            changes.addAll(action.changesFrom(pos,side,board));
        return changes;
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
