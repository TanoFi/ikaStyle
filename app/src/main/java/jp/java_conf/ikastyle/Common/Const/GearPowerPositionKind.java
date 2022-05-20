package jp.java_conf.ikastyle.Common.Const;

public enum GearPowerPositionKind {
    SUB(0), // サブギアは0
    HEAD(1), // アタマのメインは1
    CLOTHING(2), // フクのメインは2
    SHOES(3); // クツのメインは3

    private final int id;

    GearPowerPositionKind(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    // idを受け取って該当idのGearKindを返す
    public static GearPowerPositionKind getGearPowerPositionKind(int id){
        GearPowerPositionKind[] gearPowerPositionKinds = GearPowerPositionKind.values();
        for (GearPowerPositionKind item: gearPowerPositionKinds) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
