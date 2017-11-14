package ovoce.thedrake.media.plaintext;

import ovoce.thedrake.game.EmptyTile;
import ovoce.thedrake.game.TroopTile;
import ovoce.thedrake.media.PrintMedia;
import ovoce.thedrake.media.TileMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class TilePlainTextMedia extends PrintMedia implements TileMedia<Void> {
    public TilePlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putTroopTile(TroopTile tile) {
        PrintWriter w = writer();

        w.println(tile.troop().info().name() + " " + tile.troop().side().toString() + " " + tile.troop().face().toString());
        return null;
    }

    @Override
    public Void putEmptyTile(EmptyTile tile) {
        PrintWriter w = writer();

        w.println("empty");
        return null;
    }
}
