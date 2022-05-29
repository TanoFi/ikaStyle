package com.splatool.ikastyle.model.common.const

/*
 * ギアの場所種別(アタマ,フク,クツ)のEnum
 */
enum class GearKind(  // クツは3
    private val id: Int
) {
    HEAD(1),  // アタマは1
    CLOTHING(2),  // フクは2
    SHOES(3);

    fun getId(): Int {
        return id
    }

    companion object {
        // idを受け取って該当idのGearKindを返す
        fun getGearKind(id: Int): GearKind? {
            for(item: GearKind in values()){
                if (item.getId() == id) {
                    return item
                }
            }
            return null
        }
    }
}