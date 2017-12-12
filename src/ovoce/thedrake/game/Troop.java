package ovoce.thedrake.game;

import java.util.ArrayList;
import java.util.List;

public class Troop {
	private final TroopInfo info;
	private final PlayingSide side;
	private final TroopFace face;

	public Troop(TroopInfo info, PlayingSide side, TroopFace face) {
		this.info = info;
		this.side = side;
		this.face = face;
	}

	public Troop(TroopInfo info, PlayingSide side) {
		this(info, side, TroopFace.FRONT);
	}
	
	public TroopInfo info() {
		return info;
	}

	public PlayingSide side() {
		return side;
	}

	public TroopFace face() {
		return face;
	}

	public Offset2D pivot() {
		return info.pivot(face);
	}

	public Troop flipped() {
		return new Troop(
				info, 
				side, 
				face == TroopFace.FRONT ? TroopFace.BACK : TroopFace.FRONT);
	}
	
	public List<BoardChange> changesFrom(TilePosition pos, Board board) {
  	List<BoardChange> result = new ArrayList<>();
  	for(TroopAction action : info.actions(face)) {
  		result.addAll(action.changesFrom(pos, this.side, board));
  	}
  	
  	return result;
  }
}
