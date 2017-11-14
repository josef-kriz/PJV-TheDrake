
package ovoce.thedrake.media;

import ovoce.thedrake.game.MiddleGameState;
import ovoce.thedrake.game.PlacingGuardsGameState;
import ovoce.thedrake.game.PlacingLeadersGameState;
import ovoce.thedrake.game.VictoryGameState;

public interface GameStateMedia<T> {
	public T putPlacingLeadersGameState(PlacingLeadersGameState state);
	public T putPlacingGuardsGameState(PlacingGuardsGameState state);
	public T putMiddleGameState(MiddleGameState state);
	public T putFinishedGameState(VictoryGameState state);
}

