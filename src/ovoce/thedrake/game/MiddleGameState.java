package ovoce.thedrake.game;

import ovoce.thedrake.media.GameStateMedia;

import java.util.ArrayList;
import java.util.List;

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
        List<Move> result = new ArrayList<Move>();

        for(Tile tile : board()) {
            if (tile.hasTroop() && tile.troop().side() == sideOnTurn()) result.addAll(boardMoves(tile.position()));
        }

        result.addAll(stackMoves());
        return result;
	}

	@Override
	public List<Move> boardMoves(TilePosition position) {
		List<Move> result = new ArrayList<>();
		Troop troop = super.board().tileAt(position).troop();

        for (TroopAction action : troop.info().actions(troop.face()))
            for ( BoardChange change: action.changesFrom(position, sideOnTurn(), super.board()))
                result.add(new BoardMove(this, change));

		return result;
	}

	@Override
	public List<Move> stackMoves() {
		List<Move> result = new ArrayList<>();
		Troop troop = troopStacks().peek(sideOnTurn());
		for(Tile tile : board()) {
		    TilePosition new1 = new TilePosition(tile.position().i + 1, tile.position().j);
		    TilePosition new2 = new TilePosition(tile.position().i - 1, tile.position().j);
		    TilePosition new3 = new TilePosition(tile.position().i, tile.position().j + 1);
		    TilePosition new4 = new TilePosition(tile.position().i, tile.position().j - 1);

			if (tile.acceptsTroop(troop)
                    && (
                        (super.board().contains(new1)
                        && !super.board().canPlaceTo(troop, new1)
                        &&  super.board().tileAt(new1).troop().side() == sideOnTurn())
                    ||   (super.board().contains(new2)
                        && !super.board().canPlaceTo(troop, new2)
                        &&  super.board().tileAt(new2).troop().side() == sideOnTurn())
                    ||    (super.board().contains(new3)
                        && !super.board().canPlaceTo(troop, new3)
                        &&  super.board().tileAt(new3).troop().side() == sideOnTurn())
                    ||    (super.board().contains(new4)
                        && !super.board().canPlaceTo(troop, new4)
                        &&  super.board().tileAt(new4).troop().side() == sideOnTurn())
            )) {
			    result.add(new PlaceFromStack(this, tile.position()));
            }
		}

		return result;
	}

	@Override
	public <T> T putToMedia(GameStateMedia<T> media) {
		return media.putMiddleGameState(this);
	}

	@Override
	public boolean isVictory() {
		return false;
	}
		
	public boolean canPlaceFromStack(TilePosition target) {
        TilePosition new1 = new TilePosition(target.i + 1, target.j);
        TilePosition new2 = new TilePosition(target.i - 1, target.j);
        TilePosition new3 = new TilePosition(target.i, target.j + 1);
        TilePosition new4 = new TilePosition(target.i, target.j - 1);
        Troop troop = troopStacks().peek(sideOnTurn());
		return super.board().canPlaceTo(troop, target) && (
                (super.board().contains(new1)
                        && !super.board().canPlaceTo(troop, new1)
                        &&  super.board().tileAt(new1).troop().side() == sideOnTurn())
                        ||   (super.board().contains(new2)
                        && !super.board().canPlaceTo(troop, new2)
                        &&  super.board().tileAt(new2).troop().side() == sideOnTurn())
                        ||    (super.board().contains(new3)
                        && !super.board().canPlaceTo(troop, new3)
                        &&  super.board().tileAt(new3).troop().side() == sideOnTurn())
                        ||    (super.board().contains(new4)
                        && !super.board().canPlaceTo(troop, new4)
                        &&  super.board().tileAt(new4).troop().side() == sideOnTurn())
        );
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
}
