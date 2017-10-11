package ovoce.thedrake;

public class Offset2D {

    private final int x;
    private final int y;

    public Offset2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equalsTo(int x, int y) {
        return false; //TODO
    }

    public Offset2D yFlipped() {
        return this; //TODO
    }
}
