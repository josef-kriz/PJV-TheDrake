package ovoce.thedrake;

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
        if (side == PlayingSide.BLUE) playerBlue.add(0, info);
        else playerOrange.add(0, info);
        return new CapturedTroops(playerBlue, playerOrange);
    }
}
