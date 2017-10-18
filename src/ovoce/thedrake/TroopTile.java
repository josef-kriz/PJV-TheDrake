package ovoce.thedrake;

public class TroopTile extends Tile {
    private Troop troop;

    public TroopTile(TilePosition position, Troop troop){
        super(position);
        this.troop = troop;
    }

    @Override
    public boolean acceptsTroop(Troop troop) {
        return false;
    }

    @Override
    public boolean hasTroop() {
        return true;
    }

    @Override
    public Troop troop() {
        return this.troop;
    }
}
