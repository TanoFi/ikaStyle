package com.splatool.ikastyle.model.common.const

/*
 * ギアパワーの場所種別のEnum
 */
enum class GearPowerPositionKind(
    private val id: Int
) {
    HEAD_MAIN(10),
    HEAD_SUB1(11),
    HEAD_SUB2(12),
    HEAD_SUB3(13),
    CLOTHING_MAIN(20),
    CLOTHING_SUB1(21),
    CLOTHING_SUB2(22),
    CLOTHING_SUB3(23),
    SHOES_MAIN(30),
    SHOES_SUB1(31),
    SHOES_SUB2(32),
    SHOES_SUB3(33);

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