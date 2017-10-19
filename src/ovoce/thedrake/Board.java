package ovoce.thedrake;

public class Board {

    private Tile[][] board;

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru se specefikovanými dlaždicemi.
    // Všechny ostatní dlažice se berou jako prázdné.
    public Board(int dimension, Tile... tiles) {
        this.board = new Tile[dimension][dimension];
        //TODO nakopirovat tiles do this.board
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
        //TODO nejaky foreach?
        return false;
    }

    // Vytváří novou hrací desku s novými dlaždicemi z pole tiles. Všechny ostatní dlaždice zůstávají stejné
    public Board withTiles(Tile... tiles) {
        //TODO vratit i stavajici
        return new Board(this.board.length, tiles);
    }
}
