package ovoce.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;

import ovoce.thedrake.game.BaseGameState;
import ovoce.thedrake.game.MiddleGameState;
import ovoce.thedrake.game.PlacingGuardsGameState;
import ovoce.thedrake.game.PlacingLeadersGameState;
import ovoce.thedrake.game.VictoryGameState;
import ovoce.thedrake.media.GameStateMedia;
import ovoce.thedrake.media.PrintMedia;

public class GameStatePlainTextMedia extends PrintMedia implements GameStateMedia<Void>{

	private final BoardPlainTextMedia boardMedia;
	private final TroopStacksPlainTextMedia stacksMedia;
	private final LeadersPlainTextMedia leadersMedia;
	
	public GameStatePlainTextMedia(OutputStream stream) {
		super(stream);
		this.boardMedia = new BoardPlainTextMedia(stream);
		this.stacksMedia = new TroopStacksPlainTextMedia(stream);
		this.leadersMedia = new LeadersPlainTextMedia(stream);
	}

	private void putBaseGameState(BaseGameState state) {		
		writer().printf(state.sideOnTurn().toString() + "%n");
		state.troopStacks().putToMedia(stacksMedia);
		state.leaders().putToMedia(leadersMedia);
		state.board().putToMedia(boardMedia);		
	}
	
	@Override
	public Void putMiddleGameState(MiddleGameState state) {
		PrintWriter w = writer();
		w.printf("MIDDLE%n");
		w.printf("4%n");
		putBaseGameState(state);
		return null;
	}

	@Override
	public Void putPlacingLeadersGameState(PlacingLeadersGameState state) {
		PrintWriter w = writer();
		w.printf("LEADERS%n");
		w.printf("0%n");
		putBaseGameState(state);
		return null;
	}

	@Override
	public Void putPlacingGuardsGameState(PlacingGuardsGameState state) {
		PrintWriter w = writer();
		w.printf("GUARDS%n");
		w.printf("%d%n", state.guardsCount());
		putBaseGameState(state);
		return null;
	}

	@Override
	public Void putFinishedGameState(VictoryGameState state) {
		PrintWriter w = writer();
		w.printf("VICTORY%n");
		w.printf("4%n");
		putBaseGameState(state);
		return null;
	}
}
