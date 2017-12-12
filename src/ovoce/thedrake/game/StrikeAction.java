package ovoce.thedrake.game;

import java.util.Collections;
import java.util.List;

public class StrikeAction implements TroopAction {
  
	private Offset2D position;
  
  public StrikeAction(int posX, int posY) {
    this.position = new Offset2D(posX, posY);
  }
  
  @Override
  public List<BoardChange> changesFrom(TilePosition origin, PlayingSide side, Board board) {
  	TilePosition target = origin.stepByPlayingSide(position, side);
  	 
  	if(board.canCaptureOnly(origin, target)) 
  	  return Collections.singletonList(new CaptureOnly(board, origin, target));
  	
  	return Collections.emptyList();
  }
}
