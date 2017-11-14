package ovoce.thedrake.media.plaintext;

import ovoce.thedrake.game.BothLeadersPlaced;
import ovoce.thedrake.game.NoLeadersPlaced;
import ovoce.thedrake.game.OneLeaderPlaced;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.media.LeadersMedia;
import ovoce.thedrake.media.PrintMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class LeadersPlainTextMedia extends PrintMedia implements LeadersMedia<Void> {
    public LeadersPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putNoLeadersPlaced(NoLeadersPlaced leaders) {
        PrintWriter w = writer();

        w.println("NL");
        return null;
    }

    @Override
    public Void putOneLeaderPlaced(OneLeaderPlaced leaders) {
        PrintWriter w = writer();

        if(leaders.isPlaced(PlayingSide.BLUE))
            w.println("OL " + leaders.position(PlayingSide.BLUE).toString());
        else
            w.println("OL X " + leaders.position(PlayingSide.ORANGE).toString());
        return null;
    }

    @Override
    public Void putBothLeadersPlaced(BothLeadersPlaced leaders) {
        PrintWriter w = writer();

        w.println("BL " + leaders.position(PlayingSide.BLUE).toString() + " " + leaders.position(PlayingSide.ORANGE).toString());
        return null;
    }
}
