package ovoce.thedrake.game;

public class StepOnly extends BoardChange {
    public StepOnly(Board initialBoard, TilePosition origin, TilePosition target) {
        super(initialBoard, origin, target);
    }

    @Override
    public Board resultBoard() {
        return super.initialBoard().stepOnly(super.origin(),super.target());
    }
}
