package ovoce.thedrake.game;

import java.util.Iterator;

import ovoce.thedrake.media.BoardMedia;

public class Board implements Iterable<Tile> {
	private final Tile[][] grid;
	private final int dimension;
	private final CapturedTroops captured;

	public Board(int dimension, CapturedTroops captured, Tile... tiles) {
		this.dimension = dimension;
		this.grid = fillGrid(emptyGrid(), tiles);
		this.captured = captured;
	}
	
	public Board(int dimension, Tile... tiles) {
		this(dimension, new CapturedTroops(), tiles);
	}

	private Board(Tile[][] grid, CapturedTroops captured) {
		this.dimension = grid.length;
		this.grid = grid;
		this.captured = captured;
	}
	
	public int dimension() {
		return dimension;
	}
	
	public CapturedTroops captured() {
		return captured;
	}
	
	public boolean canTakeFrom(TilePosition origin) {
		if(!contains(origin)) {
			return false;
		}
		
		if(!tileAt(origin).hasTroop()) {
			return false;
		}
		
		return true;
	}
	
	public boolean canPlaceTo(Troop troop, TilePosition target) {
		if(!contains(target)) {
			return false;
		}
		
		return tileAt(target).acceptsTroop(troop);
	}
	
	public boolean canCaptureOn(Troop troop, TilePosition target) {
		if(!contains(target)) {
			return false;
		}
		
		if(!tileAt(target).hasTroop()) {
			return false;
		}
		
		return tileAt(target).troop().side() == troop.side().opposite();
	}
	
	public boolean canStepOnly(TilePosition origin, TilePosition target) {
		return 
				canTakeFrom(origin) &&
				canPlaceTo(tileAt(origin).troop(), target);
	}
	
	public boolean canCaptureOnly(TilePosition origin, TilePosition target) {
		return 
				canTakeFrom(origin) &&
				canCaptureOn(tileAt(origin).troop(), target);
	}
	
	public boolean canStepAndCapture(TilePosition origin, TilePosition target) {
		return canCaptureOnly(origin, target);
	}
	
	public Board stepOnly(TilePosition origin, TilePosition target) {
		Troop troop = tileAt(origin).troop();		
		return withTiles( 
				new EmptyTile(origin),  
				new TroopTile(target, troop.flipped())); 
	}
	
	public Board stepAndCapture(TilePosition origin, TilePosition target) {
		Troop attacker = tileAt(origin).troop();
		Troop targetTroop = tileAt(target).troop();
		
		return withCaptureAndTiles(
				targetTroop.info(),
				targetTroop.side(), 
				new EmptyTile(origin),  
				new TroopTile(target, attacker.flipped()));	
	}
	
	public Board captureOnly(TilePosition origin, TilePosition target) {
		Troop attacker = tileAt(origin).troop();
		Troop targetTroop = tileAt(target).troop();
		
		return withCaptureAndTiles(
				targetTroop.info(),
				targetTroop.side(),
				new TroopTile(origin, attacker.flipped()),  
				new EmptyTile(target));
	}
	
	public Board withTiles(Tile... tiles) {
		return new Board(fillGrid(cloneGrid(), tiles), captured);
	}
	
	public Board withCaptureAndTiles(TroopInfo info, PlayingSide side, Tile... tiles) {
		return new Board(
				fillGrid(cloneGrid(), tiles), 
				captured().withTroop(side, info));
	}
		
	private Tile[][] emptyGrid() {
		Tile[][] grid = new Tile[dimension][dimension];
		
		for(int j = 0; j < dimension; j++) {
			for(int i = 0; i < dimension; i++) {
				if(grid[i][j] == null) {
					grid[i][j] = new EmptyTile(new TilePosition(i, j));
				}
			}
		}
		
		return grid;
	}
	
	private Tile[][] fillGrid(Tile[][] grid, Tile... tiles) {		
		for(Tile tile : tiles) {
			grid[tile.position().i][tile.position().j] = tile;
		}		
		
		return grid;
	}
	
	private Tile[][] cloneGrid() {
		Tile[][] newGrid = new Tile[dimension][];
    for(int i = 0; i < grid.length; i++)
      newGrid[i] = grid[i].clone();
    
    return newGrid;
  }
	
	public Tile tileAt(TilePosition position) {
    if(!contains(position)) {
      throw new IllegalArgumentException();
    }
    
    return grid[position.i][position.j];
  }
		
	public boolean contains(TilePosition... positions) {
		for(TilePosition pos : positions) {
			if(pos.i < 0)
				return false;
			else if(pos.i >= dimension) 
				return false;
			else if(pos.j < 0)
				return false;
			else if(pos.j >= dimension) 
				return false;
		}
		
		return true;				
	}
	
	public <T> T putToMedia(BoardMedia<T> media) {
		return media.putBoard(this);
	}
	
	@Override
	public Iterator<Tile> iterator() {
		return new BoardIterator();
	}
	
	public class BoardIterator implements Iterator<Tile> {
		private int pos;
		
		public BoardIterator() {
			this.pos = 0;
		}
		
		@Override
		public boolean hasNext() {
			return pos < 16;
		}

		@Override
		public Tile next() {
			TilePosition position = new TilePosition(pos % 4, pos / 4);
			pos++;			
			return tileAt(position);
		}		
	}
}
