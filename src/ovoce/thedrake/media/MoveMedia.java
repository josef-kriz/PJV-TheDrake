package ovoce.thedrake.media;

import ovoce.thedrake.game.BoardMove;
import ovoce.thedrake.game.PlaceFromStack;
import ovoce.thedrake.game.PlaceGuard;
import ovoce.thedrake.game.PlaceLeader;

public interface MoveMedia<T> {
	public T putBoardMove(BoardMove boardMove);
	public T putPlace(PlaceFromStack place);
	public T putPlaceLeader(PlaceLeader place);
	public T putPlaceGuard(PlaceGuard place);
}
