package ovoce.thedrake.media.plaintext;

import ovoce.thedrake.game.Board;
import ovoce.thedrake.game.CapturedTroops;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.media.BoardMedia;
import ovoce.thedrake.media.CapturedTroopsMedia;
import ovoce.thedrake.media.PrintMedia;
import ovoce.thedrake.game.TroopInfo;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CapturedTroopsPlainTextMedia extends PrintMedia implements CapturedTroopsMedia<Void> {
    public CapturedTroopsPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putCapturedTroops(CapturedTroops captured) {
        PrintWriter w = writer();

        w.println("Captured BLUE: " + captured.troops(PlayingSide.BLUE).size());
        for (TroopInfo troop : captured.troops(PlayingSide.BLUE)) {
            w.println(troop.name());
        }

        w.printf("Captured ORANGE: %d",captured.troops(PlayingSide.ORANGE).size());
        for (TroopInfo troop : captured.troops(PlayingSide.ORANGE)) {
            w.printf("\n%s", troop.name());
        }
        return null;
    }
}
