package ovoce.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;

import ovoce.thedrake.game.BasicTroopStacks;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.TroopInfo;
import ovoce.thedrake.media.PrintMedia;
import ovoce.thedrake.media.TroopStacksMedia;

public class TroopStacksPlainTextMedia extends PrintMedia implements TroopStacksMedia<Void> {
	
	public TroopStacksPlainTextMedia(OutputStream stream) {
		super(stream);
	}

	@Override
	public Void putBasicTroopStacks(BasicTroopStacks stacks) {
		PrintWriter w = writer();
		
		w.printf("BLUE stack:");		
		for(TroopInfo troop : stacks.troops(PlayingSide.BLUE)) {
			w.printf(" ");
			w.printf(troop.name());
		}
		
		w.println();
		
		w.printf("ORANGE stack:");		
		for(TroopInfo troop : stacks.troops(PlayingSide.ORANGE)) {
			w.printf(" ");
			w.printf(troop.name());
		}
		
		w.println();
		return null;
	}
	
	
}
