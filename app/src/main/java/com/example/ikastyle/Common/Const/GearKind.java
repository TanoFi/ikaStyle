package com.example.ikastyle.Common.Const;

/*
 * ギアの場所種別(アタマ,フク,クツ)のEnum
 */
public enum GearKind {
    HEAD(1), // アタマは1
    CLOTHING(2), // フクは2
    SHOES(3); // クツは3

    private int id;

    GearKind(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    // idを受け取って該当idのGearKindを返す
    public static GearKind getGearKind(int id){
        GearKind[] gearKinds = GearKind.values();
        for (GearKind item: gearKinds) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
