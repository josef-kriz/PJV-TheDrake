package ovoce.thedrake.game;

import ovoce.thedrake.media.TroopStacksMedia;

import java.util.List;

public interface TroopStacks {
	public Troop peek(PlayingSide side);
	public TroopStacks pop(PlayingSide side);
	public List<TroopInfo> troops(PlayingSide side);
	public <T> T putToMedia(TroopStacksMedia<T> media);
}
