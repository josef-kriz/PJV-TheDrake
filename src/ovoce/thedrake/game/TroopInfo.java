package ovoce.thedrake.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TroopInfo {
	private final String name;
	private final Offset2D frontPivot;
	private final Offset2D backPivot;
	private final List<TroopAction> frontActions;
	private final List<TroopAction> backActions;
	
	public TroopInfo(
			String name, 
			Offset2D frontPivot, 
			Offset2D backPivot, 
			List<TroopAction> frontActions,
			List<TroopAction> backActions) {
		this.name = name;
		this.frontPivot = frontPivot;
		this.backPivot = backPivot;
		this.frontActions = new ArrayList<>(frontActions);
		this.backActions = new ArrayList<>(backActions);
	}
	
	public TroopInfo(
			String name,
			Offset2D pivot, 
			List<TroopAction> frontActions,
			List<TroopAction> backActions) {
		this(
				name, 
				pivot,
				pivot,
				new ArrayList<>(frontActions),
				new ArrayList<>(backActions));
	}
	
	public TroopInfo(
			String name,  
			List<TroopAction> frontActions,
			List<TroopAction> backActions) {
		this(
				name, 
				new Offset2D(1, 1), 
				new Offset2D(1, 1), 
				new ArrayList<>(frontActions),
				new ArrayList<>(backActions));
	}
	
	public String name() {
		return name;
	}

	public Offset2D pivot(TroopFace face) {
		return face == TroopFace.FRONT ? frontPivot : backPivot;
	}
	
	public List<TroopAction> actions(TroopFace face) {
		return face == TroopFace.FRONT ? 
				Collections.unmodifiableList(frontActions) : 
				Collections.unmodifiableList(backActions);
	}
}
