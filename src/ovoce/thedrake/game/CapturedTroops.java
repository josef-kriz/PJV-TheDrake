package ovoce.thedrake.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapturedTroops {
    private final List<TroopInfo> playerBlue;
    private final List<TroopInfo> playerOrange;

    // Konstruktor vytvářející prázdné seznamy
    public CapturedTroops() {
        this.playerBlue = new ArrayList<>();
        this.playerOrange = new ArrayList<>();
    }

    private CapturedTroops(List<TroopInfo> playerBlue, List<TroopInfo> playerOrange) {
        this.playerBlue = playerBlue;
        this.playerOrange = playerOrange;
    }

    // Vrací seznam zajatých jednotek pro daného hráče
    public List<TroopInfo> troops(PlayingSide side) {
        if (side == PlayingSide.BLUE) return Collections.unmodifiableList(playerBlue);
        else return Collections.unmodifiableList(playerOrange);
    }

    // Přidává nově zajatou jednotku na začátek seznamu zajatých jednotek daného hráče.
    public CapturedTroops withTroop(PlayingSide side, TroopInfo info) {
        List<TroopInfo> newBlue = new ArrayList<TroopInfo>();
        List<TroopInfo> newOrange = new ArrayList<TroopInfo>();
        newBlue.addAll(playerBlue);
        newOrange.addAll(playerOrange);

        if (side == PlayingSide.BLUE) newBlue.add(0, info);
        else newOrange.add(0, info);
        return new CapturedTroops(newBlue, newOrange);
    }
}
