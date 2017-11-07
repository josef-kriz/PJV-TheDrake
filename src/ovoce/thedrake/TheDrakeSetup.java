package ovoce.thedrake;

import java.util.List;
import java.util.NoSuchElementException;

public interface TheDrakeSetup {
	public List<TroopInfo> troops();

	// Vrátí info jednotky podle jména jednotky
	public TroopInfo infoByName(String name);
}
