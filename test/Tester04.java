import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ovoce.thedrake.game.BasicTroopStacks;
import ovoce.thedrake.game.Board;
import ovoce.thedrake.game.BoardMove;
import ovoce.thedrake.game.BothLeadersPlaced;
import ovoce.thedrake.game.CaptureOnly;
import ovoce.thedrake.game.CapturedTroops;
import ovoce.thedrake.game.MiddleGameState;
import ovoce.thedrake.game.Move;
import ovoce.thedrake.game.PlaceFromStack;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.StandardDrakeSetup;
import ovoce.thedrake.game.StepAndCapture;
import ovoce.thedrake.game.StepOnly;
import ovoce.thedrake.game.Tile;
import ovoce.thedrake.game.TilePosition;
import ovoce.thedrake.game.Troop;
import ovoce.thedrake.game.TroopFace;
import ovoce.thedrake.game.TroopTile;

public class Tester04 {

	private Board createTestBoard() {
		StandardDrakeSetup setup = new StandardDrakeSetup();
		Board board = new Board(
				4, 
				new CapturedTroops(),
				new TroopTile(new TilePosition("a1"), new Troop(setup.MONK, PlayingSide.BLUE)),
				new TroopTile(new TilePosition("b1"), new Troop(setup.DRAKE, PlayingSide.BLUE)),
				new TroopTile(new TilePosition("a2"), new Troop(setup.SPEARMAN, PlayingSide.BLUE)),
				new TroopTile(new TilePosition("c2"), new Troop(setup.CLUBMAN, PlayingSide.BLUE)),
				new TroopTile(new TilePosition("a4"), new Troop(setup.ARCHER, PlayingSide.ORANGE, TroopFace.BACK)),
				new TroopTile(new TilePosition("b4"), new Troop(setup.DRAKE, PlayingSide.ORANGE, TroopFace.BACK)),
				new TroopTile(new TilePosition("c3"), new Troop(setup.SWORDSMAN, PlayingSide.ORANGE)));
		return board;
	}
	
	@Test
	public void standardSetupTest() {
		StandardDrakeSetup setup = new StandardDrakeSetup();
		assertSame(setup.DRAKE, setup.infoByName("Drake"));
		assertSame(setup.CLUBMAN, setup.infoByName("Clubman"));
		assertSame(setup.MONK, setup.infoByName("Monk"));
		assertSame(setup.SPEARMAN, setup.infoByName("Spearman"));
		assertSame(setup.SWORDSMAN, setup.infoByName("Swordsman"));
		assertSame(setup.ARCHER, setup.infoByName("Archer"));
		
		try{ 
			setup.infoByName("Martin"); 
			fail(); 
		} catch(IllegalArgumentException e) {};
	}

	
	
	@Test
	public void boardTest() {
		Board board = createTestBoard();
		List<TilePosition> poss = new ArrayList<>();
		
		for(Tile tile : board) {
			poss.add(tile.position());
		}
		
		assertEquals(poss, Arrays.asList(
				new TilePosition("a1"), 
				new TilePosition("b1"),
				new TilePosition("c1"),
				new TilePosition("d1"),
				new TilePosition("a2"),
				new TilePosition("b2"),
				new TilePosition("c2"),
				new TilePosition("d2"),
				new TilePosition("a3"),
				new TilePosition("b3"),
				new TilePosition("c3"),
				new TilePosition("d3"),
				new TilePosition("a4"),
				new TilePosition("b4"),
				new TilePosition("c4"),
				new TilePosition("d4")));
	}
	
	@Test
	public void middleGameStateTest() {
		Board board = createTestBoard();
		StandardDrakeSetup setup = new StandardDrakeSetup();
		MiddleGameState state = new MiddleGameState(
				board, 
					new BasicTroopStacks(setup.CLUBMAN), 
					new BothLeadersPlaced(new TilePosition("b1"), new TilePosition("b4")), 
					PlayingSide.BLUE);
		
		Set<Move> actualFromStack = new HashSet<Move>(state.stackMoves());
		Set<Move> expectedFromStack = new HashSet<Move>();
		expectedFromStack.add(new PlaceFromStack(state, new TilePosition("c1")));
		expectedFromStack.add(new PlaceFromStack(state, new TilePosition("b2")));
		expectedFromStack.add(new PlaceFromStack(state, new TilePosition("a3")));
		expectedFromStack.add(new PlaceFromStack(state, new TilePosition("d2")));
		assertEquals(expectedFromStack, actualFromStack);
		
		Set<Move> actualAll = new HashSet<Move>(state.allMoves());
		Set<Move> expectedAll = new HashSet<Move>(expectedFromStack);
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("a1"), new TilePosition("b2"))));
		expectedAll.add(new BoardMove(state, new StepAndCapture(state.board(), new TilePosition("a1"), new TilePosition("c3"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("a2"), new TilePosition("a3"))));
		expectedAll.add(new BoardMove(state, new CaptureOnly(state.board(), new TilePosition("a2"), new TilePosition("b4"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("b1"), new TilePosition("d1"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("b1"), new TilePosition("c1"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("c2"), new TilePosition("c1"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("c2"), new TilePosition("d2"))));
		expectedAll.add(new BoardMove(state, new StepOnly(state.board(), new TilePosition("c2"), new TilePosition("b2"))));
		expectedAll.add(new BoardMove(state, new StepAndCapture(state.board(), new TilePosition("c2"), new TilePosition("c3"))));

		assertEquals(expectedAll, actualAll);
		
		assertTrue(state.canPlaceFromStack(new TilePosition("c1")));
		assertTrue(state.canPlaceFromStack(new TilePosition("b2")));
		assertTrue(state.canPlaceFromStack(new TilePosition("a3")));
		assertTrue(state.canPlaceFromStack(new TilePosition("d2")));
		
		assertFalse(state.canPlaceFromStack(new TilePosition("a1")));
		assertFalse(state.canPlaceFromStack(new TilePosition("c4")));
		assertFalse(state.canPlaceFromStack(new TilePosition("d3")));
		assertFalse(state.canPlaceFromStack(new TilePosition("d4")));
		assertFalse(state.canPlaceFromStack(new TilePosition("e1")));
	}
}

