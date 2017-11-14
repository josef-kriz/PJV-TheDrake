package ovoce.thedrake.media;

import ovoce.thedrake.game.EmptyTile;
import ovoce.thedrake.game.TroopTile;

public interface TileMedia<T> {
	public T putTroopTile(TroopTile tile);	
	public T putEmptyTile(EmptyTile tile);
}
