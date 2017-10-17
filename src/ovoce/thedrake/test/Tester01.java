package ovoce.thedrake.test;

import org.junit.Assert;
import org.junit.Test;
import ovoce.thedrake.*;


public class Tester01 {

	@Test
	public void offset2DTest() {
		Offset2D offset2D = new Offset2D(10, -5);
		Assert.assertSame(10, offset2D.x);
		Assert.assertSame(-5, offset2D.y);
		Assert.assertTrue(offset2D.equalsTo(10, -5));
		Assert.assertTrue(offset2D.yFlipped().equalsTo(10, 5));
		Assert.assertTrue(offset2D.yFlipped().yFlipped().equalsTo(10, -5));
		Assert.assertTrue(offset2D.yFlipped() != offset2D);
	}

	@Test
	public void troopInfoTest() {
		TroopInfo info = new TroopInfo("Archer", new Offset2D(1, 1), new Offset2D(0, 1));
		Assert.assertEquals("Archer", info.name());
		Assert.assertTrue(info.pivot(TroopFace.FRONT).equalsTo(1, 1));
		Assert.assertTrue(info.pivot(TroopFace.BACK).equalsTo(0, 1));
	}

	@Test
	public void troopTest() {
		TroopInfo info = new TroopInfo("Archer", new Offset2D(1, 1), new Offset2D(0, 1));
		Troop troop = new Troop(info, PlayingSide.BLUE, TroopFace.FRONT);
		Assert.assertSame(info, troop.info());
		Assert.assertEquals(PlayingSide.BLUE, troop.side());
		Assert.assertEquals(TroopFace.FRONT, troop.face());
		Assert.assertTrue(troop.pivot().equalsTo(1, 1));

		Assert.assertTrue(troop.flipped() != troop);
		Assert.assertEquals(TroopFace.BACK, troop.flipped().face());
		Assert.assertTrue(troop.flipped().pivot().equalsTo(0, 1));
	}
}
