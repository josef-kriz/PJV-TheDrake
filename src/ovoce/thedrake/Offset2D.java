package ovoce.thedrake;

public class Offset2D {

    public final int x;
    public final int y;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Offset2D test = new Offset2D(1, 2);
    }

    public Offset2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equalsTo(int x, int y) {
        return this.x == x && this.y == y;
    }

    public Offset2D yFlipped() {
        return new Offset2D(this.x, -this.y);
    }
}

