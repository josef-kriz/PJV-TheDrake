package ovoce.thedrake.media.plaintext;

import java.io.BufferedReader;
import java.io.IOException;

import ovoce.thedrake.game.BothLeadersPlaced;
import ovoce.thedrake.game.Leaders;
import ovoce.thedrake.game.NoLeadersPlaced;
import ovoce.thedrake.game.OneLeaderPlaced;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.TheDrakeSetup;
import ovoce.thedrake.game.TilePosition;

public class LeadersFromPlainText {
	private final BufferedReader reader;
	
	public LeadersFromPlainText(TheDrakeSetup setup, BufferedReader reader) {
		this.reader = reader;
	}
	
	public Leaders readLeaders() throws IOException {
		String line = reader.readLine();
		String[] parts = line.split(" ");
		
		if("NL".equals(parts[0]))
			return new NoLeadersPlaced();
		
		if("OL".equals(parts[0])) {
			if("X".equals(parts[1])) {
				return new OneLeaderPlaced(PlayingSide.ORANGE, new TilePosition(parts[2]));
			}
			
			return new OneLeaderPlaced(PlayingSide.BLUE, new TilePosition(parts[1]));
		}
		
		return new BothLeadersPlaced(
				new TilePosition(parts[1]), 
				new TilePosition(parts[2]));
	}
}

