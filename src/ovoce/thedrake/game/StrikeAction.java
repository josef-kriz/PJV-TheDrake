package ovoce.thedrake.game;

import java.util.Collections;
import java.util.List;

public class StrikeAction implements TroopAction {
    private Offset2D direction;

    public StrikeAction(int dirX, int dirY) {
        this.direction = new Offset2D(dirX, dirY);
    }

    @Override
    public List<BoardChange> changesFrom(TilePosition origin, PlayingSide side, Board board) {
        TilePosition target = origin.stepByPlayingSide(direction, side);

        if(board.canCaptureOnly(origin, target))
            return Collections.singletonList(new CaptureOnly(board, origin, target));

        return Collections.emptyList();
    }
}
