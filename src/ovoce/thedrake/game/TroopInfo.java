//popis chování jednotky ve hře
package ovoce.thedrake.game;

import java.util.Collections;
import java.util.List;

public class TroopInfo {
    private final String name;
    private final Offset2D frontPivot;
    private final Offset2D backPivot;
    private final List<TroopAction> frontActions;
    private final List<TroopAction> backActions;


    public TroopInfo(String name, Offset2D frontPivot, Offset2D backPivot) {
        this.name = name;
        this.frontPivot = frontPivot;
        this.backPivot = backPivot;
        frontActions = Collections.emptyList();
        backActions = Collections.emptyList();
    }
    public TroopInfo(String name, Offset2D frontPivot, Offset2D backPivot, List<TroopAction> frontActions, List<TroopAction> backActions) {
        this.name = name;
        this.frontPivot = frontPivot;
        this.backPivot = backPivot;
        this.frontActions = frontActions;
        this.backActions = backActions;
    }

    public TroopInfo(String name, Offset2D pivot){
        this.name = name;
        this.frontPivot = pivot;
        this.backPivot = pivot;
        frontActions = Collections.emptyList();
        backActions = Collections.emptyList();
    }

    public TroopInfo(String name, List<TroopAction> frontActions, List<TroopAction> backActions){
        this.name = name;
        this.frontPivot = new Offset2D(1,1);
        this.backPivot = new Offset2D(1,1);
        this.frontActions = frontActions;
        this.backActions = backActions;
    }

    public TroopInfo(String name){
        this.name = name;
        this.frontPivot = new Offset2D(1,1);
        this.backPivot = new Offset2D(1,1);
        frontActions = Collections.emptyList();
        backActions = Collections.emptyList();
    }

    public String name() {
        return name;
    }

    public Offset2D pivot(TroopFace face) {
        if (face == TroopFace.FRONT) return this.frontPivot;
        else return this.backPivot;
    }

    public List<TroopAction> actions(TroopFace face) {
        return face == TroopFace.FRONT ? frontActions : backActions;
    }
}

