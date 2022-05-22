package com.splatool.ikastyle.common.const

/*
 * ギアパワーの場所種別(サブ,アタマメイン,フクメイン,クツメイン)のEnum
 */
enum class GearPowerPositionKind(  // クツのメインは3
    private val id: Int
) {
    SUB(0),  // サブギアは0
    HEAD(1),  // アタマのメインは1
    CLOTHING(2),  // フクのメインは2
    SHOES(3);

    fun getId(): Int {
        return id
    }

    companion object {
        // idを受け取って該当idのGearPowerPositionKindを返す
        fun getGearPowerPositionKind(id: Int): GearPowerPositionKind? {
            for(item: GearPowerPositionKind in values()){
                if (item.getId() == id) {
                    return item
                }
            }
            return null
        }
    }
}