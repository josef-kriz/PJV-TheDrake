//Hrací plán
package ovoce.thedrake;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private Tile[][] board;

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru se specefikovanými dlaždicemi.
    // Všechny ostatní dlažice se berou jako prázdné.
    public Board(int dimension, Tile... tiles) {
        this.board = new Tile[dimension][dimension];

//        Naplnění hracího plánu prázdnými dlaždicemi
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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
        return this.board[position.i][position.j];
    }

    // Ověřuje, že pozice se nachází na hrací desce
    public boolean contains(TilePosition... positions) {
        for (TilePosition position : positions) {
            if (position.i >= dimension() || position.i < 0 || position.j >= dimension() || position.j < 0) return false;
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

        return new Board(board.length, existingTiles.toArray(new Tile[0]));
    }
}
