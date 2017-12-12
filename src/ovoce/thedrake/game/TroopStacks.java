package ovoce.thedrake.game;

import java.util.List;

import ovoce.thedrake.media.TroopStacksMedia;

public interface TroopStacks {
	public Troop peek(PlayingSide side);
	public TroopStacks pop(PlayingSide side);
	public List<TroopInfo> troops(PlayingSide side);
	public <T> T putToMedia(TroopStacksMedia<T> media);
}
