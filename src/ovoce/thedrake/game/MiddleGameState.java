package ovoce.thedrake.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ovoce.thedrake.media.GameStateMedia;

public class MiddleGameState extends BaseGameState {
		
	public MiddleGameState(
			Board board, 
			TroopStacks troopStacks,
			BothLeadersPlaced leaders,
			PlayingSide sideOnTurn) { 
		super(
				board, 
				troopStacks,
				leaders,  				 
				sideOnTurn);
	}
	
	@Override
	public BothLeadersPlaced leaders() {
		return (BothLeadersPlaced)super.leaders();
	}
	
	@Override
	public List<Move> allMoves() {
		List<Move> result = new ArrayList<>();
		for(Tile tile : board()) {
			result.addAll(boardMoves(tile.position()));			
		}
		
		result.addAll(stackMoves());
		return result;
	}	
	
	@Override
	public List<Move> boardMoves(TilePosition position) {		
		if(!board().contains(position))
			return Collections.emptyList();
		
		Tile tile = board().tileAt(position); 
		if(!tile.hasTroop())
			return Collections.emptyList();
		
		Troop troop = tile.troop(); 
		if(troop.side() != sideOnTurn())
			return Collections.emptyList();
		
		List<Move> result = new ArrayList<>();
		for(BoardChange c : troop.changesFrom(position, board())) {
			result.add(new BoardMove(this, c));
		}
		
		return result;
	}
	
	@Override
	public List<Move> stackMoves() {
		Troop troop = troopStacks().peek(sideOnTurn());
		Set<TilePosition> neighbours = new HashSet<TilePosition>();	
		
		for(Tile tile : board()) {
			if(!tile.hasTroop())
				continue;
			 
			if(tile.troop().side() != sideOnTurn())
				continue;
			
			tryAddTile(tile, 0, 1, troop, neighbours);
			tryAddTile(tile, 1, 0, troop, neighbours);
			tryAddTile(tile, 0, -1, troop, neighbours);
			tryAddTile(tile, -1, 0, troop, neighbours);
		}
		
		List<Move> result = new ArrayList<>();
		for(TilePosition target : neighbours) {
			result.add(new PlaceFromStack(this, target));
		}
		
		return result;
	}
	
	@Override
	public boolean isVictory() {
		return false;
	}

	private void tryAddTile(Tile tile, int iStep, int jStep, Troop troop, Set<TilePosition> result) {
		TilePosition target = tile.position().step(iStep, jStep);
		if(!board().contains(target))
			return;
		
		if(board().tileAt(target).acceptsTroop(troop))
			result.add(target);
	}
		
	public boolean canPlaceFromStack(TilePosition target) {
		if(troopStacks().troops(sideOnTurn()).isEmpty()) {
			return false;
		}
		
		if(!board().contains(target)) {
			return false;
		}		
		
		Troop troop = troopStacks().peek(sideOnTurn());
		return board().tileAt(target).acceptsTroop(troop);	
	}

	public MiddleGameState placeFromStack(TilePosition target) {
		Troop troop = troopStacks().peek(sideOnTurn());
		return new MiddleGameState(
				board().withTiles(  
					new TroopTile(target, troop)),  
				troopStacks().pop(sideOnTurn()),
				leaders(),
				sideOnTurn().opposite());
	}
	
	@Override
	public <T> T putToMedia(GameStateMedia<T> media) {
		return media.putMiddleGameState(this);
	}
}
