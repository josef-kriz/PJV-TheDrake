package ovoce.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;

import ovoce.thedrake.game.BothLeadersPlaced;
import ovoce.thedrake.game.NoLeadersPlaced;
import ovoce.thedrake.game.OneLeaderPlaced;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.media.LeadersMedia;
import ovoce.thedrake.media.PrintMedia;

public class LeadersPlainTextMedia extends PrintMedia implements LeadersMedia<Void> {
	
	public LeadersPlainTextMedia(OutputStream stream) {
		super(stream);
	}

	@Override
	public Void putNoLeadersPlaced(NoLeadersPlaced leaders) {
		writer().println("NL");
		return null;
	}
	
	@Override
	public Void putOneLeaderPlaced(OneLeaderPlaced leaders) {
		PrintWriter w = writer();
		w.printf("OL");
		if(leaders.isPlaced(PlayingSide.BLUE)) {
			w.printf(" ");
			w.printf(leaders.position(PlayingSide.BLUE).toString());			
		}
		else {
			w.printf(" X");
		}
		
		if(leaders.isPlaced(PlayingSide.ORANGE)) {
			w.printf(" ");
			w.printf(leaders.position(PlayingSide.ORANGE).toString());
		}
	
		w.println();
		return null;
	}
	
	@Override
	public Void putBothLeadersPlaced(BothLeadersPlaced leaders) {
		PrintWriter w = writer();
		w.printf("BL %s %s%n", 
				leaders.position(PlayingSide.BLUE).toString(),
				leaders.position(PlayingSide.ORANGE).toString());
		return null;
	}	
}
