//Hrací plán
package ovoce.thedrake;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private Tile[][] board;
    private CapturedTroops capturedTroops;

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru se specefikovanými dlaždicemi.
    // Všechny ostatní dlažice se berou jako prázdné.
    public Board(int dimension, Tile... tiles) {
        this.board = new Tile[dimension][dimension];
        this.capturedTroops = new CapturedTroops();
        fillBoard(tiles);
    }

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru se specefikovanými dlaždicemi.
    // Všechny ostatní dlažice se berou jako prázdné.
    public Board(int dimension, CapturedTroops oldTroops, Tile... tiles) {
        this.board = new Tile[dimension][dimension];
        fillBoard(tiles);
        this.capturedTroops = oldTroops;
    }

    private void fillBoard(Tile[] tiles) {
        //        Naplnění hracího plánu prázdnými dlaždicemi
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                this.board[i][j] = new EmptyTile(new TilePosition(i, j));
            }
        }

//        Zanesení zadaných dlaždic do plánu
        for (Tile tile : tiles) {
            this.board[tile.position().i][tile.position().j] = tile;
        }
    }
    // Rozměr hrací desky
    public int dimension() {
        return this.board.length;
    }

    // Vrací dlaždici na zvolené pozici. Pokud je pozice mimo desku, vyhazuje IllegalArgumentException
    public Tile tileAt(TilePosition position) {
        if( contains( position))
            return this.board[position.i][position.j];
        throw new IllegalArgumentException();
    }

    // Ověřuje, že pozice se nachází na hrací desce
    public boolean contains(TilePosition... positions) {
        for (TilePosition position : positions) {
            if (position.i >= dimension() || position.i < 0 || position.j >= dimension() || position.j < 0)
                return false;
        }
        return true;
    }

    // Vytváří novou hrací desku s novými dlaždicemi z pole tiles. Všechny ostatní dlaždice zůstávají stejné
    public Board withTiles(Tile... tiles) {
        ArrayList<Tile> existingTiles = new ArrayList<>();

        for (Tile[] boardTiles : board) {
            for (Tile boardTile : boardTiles) {
                if (boardTile.hasTroop()) existingTiles.add(boardTile);
            }
        }

        existingTiles.addAll(Arrays.asList(tiles));

        return new Board(board.length, capturedTroops, existingTiles.toArray(new Tile[0]));
    }

    //    nová board i se zajatými figurkami
    public Board withCaptureAndTiles(TroopInfo info, PlayingSide side, Tile... tiles) {
        Board newBoard = withTiles(tiles);
        newBoard.capturedTroops = capturedTroops.withTroop(side, info);

        return newBoard;
    }

    // Vrací zajaté jednotky
    public CapturedTroops captured() {
        return capturedTroops;
    }

    // Stojí na pozici origin jednotka?
    public boolean canTakeFrom(TilePosition origin) {
        if (contains(origin)) {
            return tileAt(origin).hasTroop();
        }
        return false;
    }

    /*
     * Lze na danou pozici postavit zadanou jednotku? Zde se řeší pouze
     * jednotky na hrací ploše, nikoliv zásobník, takže se v podstatě
     * pouze ptám, zda dlaždice na pozici target přijme danou jednotku.
     */
    public boolean canPlaceTo(Troop troop, TilePosition target) {
        if( contains(target)){
            return tileAt(target).acceptsTroop( troop );
        }
        return false;
    }

    // Může zadaná jednotka zajmout na pozici target soupeřovu jednotku?
    public boolean canCaptureOn(Troop troop, TilePosition target) {
        if( canTakeFrom( target ) && troop.side() != tileAt(target).troop().side() ) {
            return true;
        }
        return false;
    }

/*
 * Stojí na políčku origin jednotka, která může udělat krok na pozici target
 * bez toho, aby tam zajala soupeřovu jednotku?
 */
        public boolean canStepOnly (TilePosition origin, TilePosition target) {
            if ( canTakeFrom( origin ) ) {
                return canPlaceTo( tileAt(origin).troop(), target );
            }
            return false;
        }

/*
 * Stojí na políčku origin jednotka, která může zůstat na pozici origin
 * a zajmout soupeřovu jednotku na pozici target?
 */
        public boolean canCaptureOnly (TilePosition origin, TilePosition target) {
            if( canTakeFrom( origin )) {
                return canCaptureOn(tileAt(origin).troop(), target);
            }
            return false;
        }

/*
 * Stojí na pozici origin jednotka, která může udělat krok na pozici target
 * a zajmout tam soupeřovu jednotku?
 */
        public boolean canStepAndCapture (TilePosition origin, TilePosition target) {
            if( canTakeFrom( origin )) {
                return canCaptureOn(tileAt(origin).troop(), target);
            }
            return false;
        }

/*
 * Nová hrací deska, ve které jednotka na pozici origin se přesunula
 * na pozici target bez toho, aby zajala soupeřovu jednotku.
 */
        public Board stepOnly (TilePosition origin, TilePosition target){
           return withTiles(
                new EmptyTile(origin),
                new TroopTile(target, tileAt(origin).troop().flipped())
            );
        }

/*
 * Nová hrací deska, ve které jednotka na pozici origin se přesunula
 * na pozici target, kde zajala soupeřovu jednotku.
 */
        public Board stepAndCapture(TilePosition origin, TilePosition target) {
        Troop attacker = tileAt(origin).troop();
        Troop targetTroop = tileAt(target).troop();

        return withCaptureAndTiles(
                targetTroop.info(),
                targetTroop.side(),
                new EmptyTile(origin),
                new TroopTile(target, attacker.flipped()));
    }

/*
 * Nová hrací deska, ve které jednotka zůstává stát na pozici origin
 * a zajme soupeřovu jednotku na pozici target.
 */
        public Board captureOnly (TilePosition origin, TilePosition target){
            return withCaptureAndTiles(
                    tileAt(target).troop().info(), tileAt(target).troop().side(),
                    new EmptyTile(target), new TroopTile(origin, tileAt(origin).troop().flipped())
            );
        }
}
