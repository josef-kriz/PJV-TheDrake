package ovoce.thedrake.media.plaintext;

import java.io.OutputStream;

import ovoce.thedrake.game.EmptyTile;
import ovoce.thedrake.game.Troop;
import ovoce.thedrake.game.TroopTile;
import ovoce.thedrake.media.PrintMedia;
import ovoce.thedrake.media.TileMedia;

public class TilePlainTextMedia extends PrintMedia implements TileMedia<Void> {

	protected TilePlainTextMedia(OutputStream stream) {
		super(stream);
	}
	
	@Override
	public Void putTroopTile(TroopTile tile) {
		Troop troop = tile.troop();
		writer().printf(
				"%s %s %s", 
				troop.info().name(), 
				troop.side().toString(), 
				troop.face().toString());
		
		return null;
	}
	
	@Override
	public Void putEmptyTile(EmptyTile tile) {
		writer().printf("empty");
		return null;
	}
}
