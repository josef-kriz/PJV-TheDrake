package ovoce.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;

import ovoce.thedrake.game.CapturedTroops;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.TroopInfo;
import ovoce.thedrake.media.CapturedTroopsMedia;
import ovoce.thedrake.media.PrintMedia;

public class CapturedTroopsPlainTextMedia extends PrintMedia implements CapturedTroopsMedia<Void> {
	
	public CapturedTroopsPlainTextMedia(OutputStream stream) {
		super(stream);
	}
	
	@Override
	public Void putCapturedTroops(CapturedTroops captured) {
		PrintWriter w = writer();

		w.printf("Captured BLUE: %d", captured.troops(PlayingSide.BLUE).size());
		for(TroopInfo info : captured.troops(PlayingSide.BLUE)) {
			w.println();
			w.printf(info.name());
		}
		
		w.println();
		
		w.printf("Captured ORANGE: %d", captured.troops(PlayingSide.ORANGE).size());
		for(TroopInfo info : captured.troops(PlayingSide.ORANGE)) {
			w.println();
			w.printf(info.name());
		}
		
		return null;
	}
}
