package ovoce.thedrake.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapturedTroops {
	private final List<TroopInfo> blueTroops;
	private final List<TroopInfo> orangeTroops;
	
	public CapturedTroops() {
		blueTroops = Collections.emptyList();
		orangeTroops = Collections.emptyList();
	}
	
	public CapturedTroops(List<TroopInfo> blueTroops, List<TroopInfo> orangeTroops) {
		this.blueTroops = blueTroops;
		this.orangeTroops = orangeTroops;
	}
	
	public List<TroopInfo> troops(PlayingSide side) {
		return side == PlayingSide.BLUE ? 
				Collections.unmodifiableList(blueTroops) :
				Collections.unmodifiableList(orangeTroops);
	}
	
	public CapturedTroops withTroop(PlayingSide side, TroopInfo info) {
		List<TroopInfo> newTroops = new ArrayList<>();
		newTroops.add(info);
		if(side == PlayingSide.BLUE) {
			newTroops.addAll(blueTroops);
			return new CapturedTroops(newTroops, orangeTroops);
		}
		
		newTroops.addAll(orangeTroops);
		return new CapturedTroops(blueTroops, newTroops);
	}
}
