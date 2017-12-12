package ovoce.thedrake.game;

import ovoce.thedrake.media.TileMedia;

public class TroopTile extends Tile {
	
	private final Troop troop;
	
	public TroopTile(TilePosition position, Troop troop) {
  	super(position);
		this.troop = troop;
  }
	
	@Override
  public boolean acceptsTroop(Troop troop) {
   	return false;
  }  
	
  @Override
  public boolean hasTroop() {
  	return true;
  }
	
  @Override
  public Troop troop() {
  	return troop;
  }
  
  @Override
  public <T> T putToMedia(TileMedia<T> media) {
  	return media.putTroopTile(this);
  }
}
