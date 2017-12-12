package ovoce.thedrake.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardDrakeSetup implements TheDrakeSetup {
	
	private final Map<String, TroopInfo> infoMap;
	
	public StandardDrakeSetup() {
		infoMap = new HashMap<>();
		infoMap.put(DRAKE.name(), DRAKE);
		infoMap.put(CLUBMAN.name(), CLUBMAN);
		infoMap.put(MONK.name(), MONK);
		infoMap.put(SPEARMAN.name(), SPEARMAN);
		infoMap.put(SWORDSMAN.name(), SWORDSMAN);
		infoMap.put(ARCHER.name(), ARCHER);
	}
	
	@Override
	public List<TroopInfo> troops() {
		return Arrays.asList(DRAKE, CLUBMAN, MONK, SPEARMAN, SWORDSMAN, ARCHER);
	}	
	
	@Override
	public TroopInfo infoByName(String name) {
		if(infoMap.containsKey(name))
			return infoMap.get(name);
		
		throw new IllegalArgumentException();
	}
	
	public final TroopInfo DRAKE = new TroopInfo(
			"Drake",
			Arrays.asList(
					new SlideAction(1, 0),    
					new SlideAction(-1, 0)), 		      
			Arrays.asList(
					new SlideAction(0, 1),
  				new SlideAction(0, -1)));
	
	public final TroopInfo CLUBMAN = new TroopInfo(
			"Clubman",
			Arrays.asList(
		      new ShiftAction(1, 0),    
		      new ShiftAction(0, 1),
		      new ShiftAction(-1, 0),
		      new ShiftAction(0, -1)), 		      
			Arrays.asList(
		  		new ShiftAction(1, 1),    
		      new ShiftAction(-1, 1),
		      new ShiftAction(1, -1),
		      new ShiftAction(-1, -1))); 
	
	public final TroopInfo MONK = new TroopInfo(
			"Monk",
			Arrays.asList(
					new SlideAction(1, 1),    
		      new SlideAction(-1, 1),
		      new SlideAction(1, -1),
		      new SlideAction(-1, -1)), 		      
			Arrays.asList(
					new ShiftAction(1, 0),    
		      new ShiftAction(0, 1),
		      new ShiftAction(-1, 0),
		      new ShiftAction(0, -1)));
	
	public final TroopInfo SPEARMAN = new TroopInfo(
			"Spearman",
			Arrays.asList(
					new ShiftAction(0, 1),
					new StrikeAction(1, 2),    
		      new StrikeAction(-1, 2)), 		      
			Arrays.asList(
					new ShiftAction(1, 1),    
		      new ShiftAction(-1, 1),
		      new ShiftAction(0, -1)));
	
	public final TroopInfo SWORDSMAN = new TroopInfo(
			"Swordsman",
			Arrays.asList(
					new StrikeAction(1, 0),    
		      new StrikeAction(0, 1),
		      new StrikeAction(-1, 0),
		      new StrikeAction(0, -1)), 		      
			Arrays.asList(
					new ShiftAction(1, 0),    
		      new ShiftAction(0, 1),
		      new ShiftAction(-1, 0),
		      new ShiftAction(0, -1)));
	
	public final TroopInfo ARCHER = new TroopInfo(
			"Archer",
			new Offset2D(1, 1), 
			new Offset2D(1, 0),
			Arrays.asList(
					new ShiftAction(1, 0),    
		      new ShiftAction(-1, 0),
		      new ShiftAction(0, -1)), 		      
			Arrays.asList(    
		      new ShiftAction(0, 1),
		      new StrikeAction(-1, 1),
		      new StrikeAction(1, 1),
		      new StrikeAction(0, 2)));
}

