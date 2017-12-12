package ovoce.thedrake.media.plaintext;

import java.io.BufferedReader;
import java.io.IOException;

import ovoce.thedrake.game.Board;
import ovoce.thedrake.game.CapturedTroops;
import ovoce.thedrake.game.TheDrakeSetup;
import ovoce.thedrake.game.Tile;
import ovoce.thedrake.game.TilePosition;

public class BoardFromPlainText {
	private final TheDrakeSetup setup;
	private final BufferedReader reader;
	
	public BoardFromPlainText(TheDrakeSetup setup, BufferedReader reader) {
		this.setup = setup;
		this.reader = reader;
	}
	
	public Board readBoard() throws IOException {
		String line = reader.readLine();
		int dimension = Integer.parseInt(line);
		
		int tileCount = dimension*dimension;
		Tile[] tiles = new Tile[tileCount];
		
		TileFromPlainText tileFromReader = new TileFromPlainText(setup, reader);
		for(int i = 0; i < tileCount; i++) {
			TilePosition pos = new TilePosition(i % dimension, i / dimension);
			tiles[i] = tileFromReader.readTile(pos);
		}
		
		CapturedTroops captured = 
				new CapturedTroopsFromPlainText(setup, reader).readTroops();
		
		return new Board(dimension, captured, tiles);
	}
}
