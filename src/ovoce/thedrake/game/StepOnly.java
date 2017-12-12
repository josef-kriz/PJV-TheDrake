package ovoce.thedrake.game;

public class StepOnly extends BoardChange {
	
	public StepOnly(Board board, TilePosition origin, TilePosition target) {
		super(board, origin, target);
	}

	@Override
	public Board resultBoard() {
		return initialBoard().stepOnly(origin(), target());				
	}
}
