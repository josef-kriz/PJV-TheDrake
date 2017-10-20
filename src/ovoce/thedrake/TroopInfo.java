//popis chování jednotky ve hře
package ovoce.thedrake;

public class TroopInfo {
    private final String name;
    private final Offset2D frontPivot;
    private final Offset2D backPivot;

    public TroopInfo(String name, Offset2D frontPivot, Offset2D backPivot) {
        this.name = name;
        this.frontPivot = frontPivot;
        this.backPivot = backPivot;
    }

    public TroopInfo(String name, Offset2D pivot){
        this.name = name;
        this.frontPivot = pivot;
        this.backPivot = pivot;
    }

    public TroopInfo(String name){
        this.name = name;
        this.frontPivot = new Offset2D(1,1);
        this.backPivot = new Offset2D(1,1);
    }

    public String name() {
        return name;
    }

    public Offset2D pivot(TroopFace face) {
        if (face == TroopFace.FRONT) return this.frontPivot;
        else return this.backPivot;
    }
}
