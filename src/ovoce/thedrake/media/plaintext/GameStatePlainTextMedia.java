package ovoce.thedrake.media.plaintext;

import ovoce.thedrake.game.*;
import ovoce.thedrake.media.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class GameStatePlainTextMedia extends PrintMedia implements GameStateMedia<Void> {
    private final TroopStacksPlainTextMedia troopMedia;
    private final LeadersPlainTextMedia leadersMedia;
    private final BoardPlainTextMedia boardMedia;

    public GameStatePlainTextMedia(OutputStream stream) {
        super(stream);
        this.troopMedia = new TroopStacksPlainTextMedia(stream);
        this.leadersMedia = new LeadersPlainTextMedia(stream);
        this.boardMedia = new BoardPlainTextMedia(stream);
    }

    @Override
    public Void putPlacingLeadersGameState(PlacingLeadersGameState state) {
        PrintWriter w = writer();

        w.println("LEADERS");
        w.println("0");
        w.println(state.sideOnTurn().toString());
        troopMedia.putBasicTroopStacks((BasicTroopStacks)state.troopStacks());
        if(state.leaders().isPlaced(PlayingSide.BLUE))
            leadersMedia.putOneLeaderPlaced((OneLeaderPlaced)state.leaders());
        else
            leadersMedia.putNoLeadersPlaced((NoLeadersPlaced)state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putPlacingGuardsGameState(PlacingGuardsGameState state) {
        PrintWriter w = writer();

        w.println("GUARDS");
        w.println(state.guardsCount());
        w.println(state.sideOnTurn().toString());
        troopMedia.putBasicTroopStacks((BasicTroopStacks)state.troopStacks());
        leadersMedia.putBothLeadersPlaced(state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putMiddleGameState(MiddleGameState state) {
        PrintWriter w = writer();

        w.println("MIDDLE");
        w.println("4");
        w.println(state.sideOnTurn().toString());
        troopMedia.putBasicTroopStacks((BasicTroopStacks)state.troopStacks());
        leadersMedia.putBothLeadersPlaced(state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putFinishedGameState(VictoryGameState state) {
        PrintWriter w = writer();

        w.println("VICTORY");
        w.println("4");
        w.println(state.sideOnTurn().toString());
        troopMedia.putBasicTroopStacks((BasicTroopStacks)state.troopStacks());
        leadersMedia.putOneLeaderPlaced((OneLeaderPlaced)state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }
}
