package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import ovoce.thedrake.game.BasicTroopStacks;
import ovoce.thedrake.game.BoardMove;
import ovoce.thedrake.game.MiddleGameState;
import ovoce.thedrake.game.PlaceFromStack;
import ovoce.thedrake.game.PlacingGuardsGameState;
import ovoce.thedrake.game.PlacingLeadersGameState;
import ovoce.thedrake.game.StandardDrakeSetup;
import ovoce.thedrake.game.StepAndCapture;
import ovoce.thedrake.game.StepOnly;
import ovoce.thedrake.game.TilePosition;
import ovoce.thedrake.game.VictoryGameState;
import ovoce.thedrake.media.plaintext.GameStatePlainTextMedia;

public class Tester05 {

	private PlacingLeadersGameState createTestState() {
		StandardDrakeSetup setup = new StandardDrakeSetup();
		
		return new PlacingLeadersGameState(
				new BasicTroopStacks(
						setup.DRAKE, 
						setup.CLUBMAN,
						setup.CLUBMAN,
						setup.MONK, 
						setup.SPEARMAN,
						setup.SWORDSMAN,
						setup.ARCHER));
	}
	
	@Test
	public void mediaTest() throws IOException {
		ByteArrayOutputStream baos01 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media01 = new GameStatePlainTextMedia(baos01);		
		PlacingLeadersGameState state01 = createTestState();
		state01.putToMedia(media01);
		baos01.close();
		String expected01 = 
				"LEADERS\n" +
				"0\n" +
				"BLUE\n" + 
				"BLUE stack: Drake Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Drake Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"NL\n" +
				"4\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Captured BLUE: 0\n" +
				"Captured ORANGE: 0";		
		assertEquals(expected01, baos01.toString());
		
		ByteArrayOutputStream baos02 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media02 = new GameStatePlainTextMedia(baos02);
		PlacingLeadersGameState state02 = state01.placeBlueLeader(new TilePosition("a1"));
		state02.putToMedia(media02);
		baos02.close();
		String expected02 = 
				"LEADERS\n" +
				"0\n" +
				"ORANGE\n" +
				"BLUE stack: Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Drake Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"OL a1\n" +
				"4\n" +
				"Drake BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Captured BLUE: 0\n" +
				"Captured ORANGE: 0";
		assertEquals(expected02, baos02.toString());
		
		ByteArrayOutputStream baos03 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media03 = new GameStatePlainTextMedia(baos03);
		PlacingGuardsGameState state03 = state02.placeOrangeLeader(new TilePosition("b4"));
		state03.putToMedia(media03);
		baos03.close();
		String expected03 = 
				"GUARDS\n" +
				"0\n" +
				"BLUE\n" +
				"BLUE stack: Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"BL a1 b4\n" +
				"4\n" +
				"Drake BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Drake ORANGE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"Captured BLUE: 0\n" +
				"Captured ORANGE: 0";
		assertEquals(expected03, baos03.toString());
		
		ByteArrayOutputStream baos04 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media04 = new GameStatePlainTextMedia(baos04);
		PlacingGuardsGameState state04 = (PlacingGuardsGameState)state03.placeGuard(new TilePosition("b1"));
		state04.putToMedia(media04);
		baos04.close();
		String expected04 = 
				"GUARDS\n" +
				"1\n" +
				"ORANGE\n" +
				"BLUE stack: Clubman Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Clubman Clubman Monk Spearman Swordsman Archer\n" +
				"BL a1 b4\n" +
				"4\n" +
				"Drake BLUE FRONT\n" +
				"Clubman BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Drake ORANGE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"Captured BLUE: 0\n" +
				"Captured ORANGE: 0";
		assertEquals(expected04, baos04.toString());
				
		ByteArrayOutputStream baos05 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media05 = new GameStatePlainTextMedia(baos05);
		PlacingGuardsGameState tmp1 = (PlacingGuardsGameState)state04.placeGuard(new TilePosition("c4")); 
		PlacingGuardsGameState tmp2 = (PlacingGuardsGameState)tmp1.placeGuard(new TilePosition("a2"));
		MiddleGameState state05 = (MiddleGameState)tmp2.placeGuard(new TilePosition("b3"));		
		state05.putToMedia(media05);
		baos05.close();
		String expected05 = 
				"MIDDLE\n" +
				"4\n" +
				"BLUE\n" +
				"BLUE stack: Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Monk Spearman Swordsman Archer\n" +
				"BL a1 b4\n" +
				"4\n" +
				"Drake BLUE FRONT\n" +
				"Clubman BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"Clubman BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Clubman ORANGE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Drake ORANGE FRONT\n" +
				"Clubman ORANGE FRONT\n" +
				"empty\n" +
				"Captured BLUE: 0\n" +
				"Captured ORANGE: 0";
		assertEquals(expected05, baos05.toString());
		
		ByteArrayOutputStream baos06 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media06 = new GameStatePlainTextMedia(baos06);
		MiddleGameState tmp3 = (MiddleGameState)new BoardMove(state05, new StepOnly(
				state05.board(), 
				new TilePosition("a2"), 
				new TilePosition("a3"))).resultState(); 
		MiddleGameState tmp4 = (MiddleGameState)new BoardMove(tmp3, new StepAndCapture(
				tmp3.board(), 
				new TilePosition("b3"), 
				new TilePosition("a3"))).resultState();
		MiddleGameState tmp5 = (MiddleGameState)new BoardMove(tmp4, new StepOnly(
				tmp4.board(), 
				new TilePosition("b1"), 
				new TilePosition("b2"))).resultState();
		MiddleGameState tmp6 = (MiddleGameState)new PlaceFromStack(tmp5, 
				new TilePosition("d4")).resultState();
		MiddleGameState state06 = (MiddleGameState)new BoardMove(tmp6, new StepAndCapture(
				tmp6.board(), 
				new TilePosition("b2"), 
				new TilePosition("a3"))).resultState();
		
		state06.putToMedia(media06);
		baos06.close();
		String expected06 = 
				"MIDDLE\n" +
				"4\n" +
				"ORANGE\n" +
				"BLUE stack: Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Spearman Swordsman Archer\n" +
				"BL a1 b4\n" +
				"4\n" +
				"Drake BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Clubman BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Drake ORANGE FRONT\n" +
				"Clubman ORANGE FRONT\n" +
				"Monk ORANGE FRONT\n" +
				"Captured BLUE: 1\n" +
				"Clubman\n" +
				"Captured ORANGE: 1\n" +
				"Clubman";
		assertEquals(expected06, baos06.toString());
		
		ByteArrayOutputStream baos07 = new ByteArrayOutputStream();
		GameStatePlainTextMedia media07 = new GameStatePlainTextMedia(baos07);
		VictoryGameState state07 = (VictoryGameState)new BoardMove(state06, new StepAndCapture(
				state06.board(), 
				new TilePosition("d4"), 
				new TilePosition("a1"))).resultState(); 
		state07.putToMedia(media07);
		baos07.close();
		String expected07 = 
				"VICTORY\n" +
				"4\n" +
				"BLUE\n" +
				"BLUE stack: Monk Spearman Swordsman Archer\n" +
				"ORANGE stack: Spearman Swordsman Archer\n" +
				"OL X b4\n" +
				"4\n" +
				"Monk ORANGE BACK\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Clubman BLUE FRONT\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"empty\n" +
				"Drake ORANGE FRONT\n" +
				"Clubman ORANGE FRONT\n" +
				"empty\n" +
				"Captured BLUE: 2\n" +
				"Drake\n" +
				"Clubman\n" +
				"Captured ORANGE: 1\n" +
				"Clubman";
		assertEquals(expected07, baos07.toString());
	}
}

