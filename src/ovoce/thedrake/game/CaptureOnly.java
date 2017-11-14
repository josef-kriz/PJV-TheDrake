package ovoce.thedrake.game;

public class CaptureOnly extends BoardChange {
    public CaptureOnly(Board initialBoard, TilePosition origin, TilePosition target) {
        super(initialBoard, origin, target);
    }

    @Override
    public Board resultBoard() {
        return super.initialBoard().captureOnly(super.origin(),super.target());
    }
}
