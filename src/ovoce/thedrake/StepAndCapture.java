package ovoce.thedrake;

public class StepAndCapture extends BoardChange {
    public StepAndCapture(Board initialBoard, TilePosition origin, TilePosition target) {
        super(initialBoard, origin, target);
    }

    @Override
    public Board resultBoard() {
        return super.initialBoard().stepAndCapture(super.origin(),super.target());
    }
}
