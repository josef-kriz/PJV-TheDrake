package ovoce.thedrake.media.plaintext;

import java.io.BufferedReader;
import java.io.IOException;

import ovoce.thedrake.game.EmptyTile;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.TheDrakeSetup;
import ovoce.thedrake.game.Tile;
import ovoce.thedrake.game.TilePosition;
import ovoce.thedrake.game.Troop;
import ovoce.thedrake.game.TroopFace;
import ovoce.thedrake.game.TroopInfo;
import ovoce.thedrake.game.TroopTile;

public class TileFromPlainText {
	private final TheDrakeSetup setup;
	private final BufferedReader reader;
	
	public TileFromPlainText(TheDrakeSetup setup, BufferedReader reader) {
		this.setup = setup;
		this.reader = reader;
	}
	
	public Tile readTile(TilePosition position) throws IOException {
		String line = reader.readLine();
		
		if("empty".equals(line)) {
			return new EmptyTile(position);
		}
		
		String[] fields = line.split(" ");
		TroopInfo info = setup.infoByName(fields[0]);
		PlayingSide side = PlayingSide.valueOf(fields[1]); 
		TroopFace face = TroopFace.valueOf(fields[2]);
		Troop troop = new Troop(info, side, face);		
		return new TroopTile(position, troop);
	}
}
