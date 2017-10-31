package ovoce.thedrake;

import java.util.ArrayList;
import java.util.List;

public class StrikeAction implements TroopAction {
    private Offset2D direction;

    public StrikeAction(int dirX, int dirY) {
        this.direction = new Offset2D(dirX, dirY);
    }

    @Override
    public List<BoardChange> changesFrom(TilePosition origin, PlayingSide side, Board board) {
        List<BoardChange> result = new ArrayList<>();
        TilePosition target = origin.stepByPlayingSide(direction, side);

        while(board.canCaptureOnly(origin, target)) {
            result.add(new CaptureOnly(board, origin, target));
            target = target.stepByPlayingSide(direction, side);
        }

        return result;
    }
}
