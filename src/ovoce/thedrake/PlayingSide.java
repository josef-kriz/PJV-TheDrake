//výčtový typ představující barvu příslušící každému hráči
package ovoce.thedrake;

public enum PlayingSide {
    BLUE, ORANGE {
        @Override
        public PlayingSide opposite() {
            return BLUE;
        }
    };

    public PlayingSide opposite() {
        return ORANGE;
    }
}
