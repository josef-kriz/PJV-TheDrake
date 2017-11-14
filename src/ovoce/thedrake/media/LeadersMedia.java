package ovoce.thedrake.media;

import ovoce.thedrake.game.BothLeadersPlaced;
import ovoce.thedrake.game.NoLeadersPlaced;
import ovoce.thedrake.game.OneLeaderPlaced;

public interface LeadersMedia<T> {
	public T putNoLeadersPlaced(NoLeadersPlaced leaders);
	public T putOneLeaderPlaced(OneLeaderPlaced leaders);
	public T putBothLeadersPlaced(BothLeadersPlaced leaders);
}
