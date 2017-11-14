package ovoce.thedrake.media.plaintext;

import ovoce.thedrake.game.BasicTroopStacks;
import ovoce.thedrake.game.PlayingSide;
import ovoce.thedrake.game.TroopInfo;
import ovoce.thedrake.media.PrintMedia;
import ovoce.thedrake.media.TroopStacksMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class TroopStacksPlainTextMedia extends PrintMedia implements TroopStacksMedia<Void> {
    public TroopStacksPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putBasicTroopStacks(BasicTroopStacks stacks) {
        PrintWriter w = writer();

        w.print("BLUE stack:");
        for (TroopInfo troop:stacks.troops(PlayingSide.BLUE) ) {
            w.print(" " + troop.name());
        }
        w.println();

        w.print("ORANGE stack:");
        for (TroopInfo troop:stacks.troops(PlayingSide.ORANGE) ) {
            w.print(" " + troop.name());
        }
        w.println();
        return null;
    }
}
