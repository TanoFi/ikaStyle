package com.splatool.ikastyle.model.common.const

/*
 * ブキの絶対的なIDを取得するための定数
 * ブキID = categoryId * CATEGORY_PLACE + mainId * MAIN_PLACE + weaponId * WEAPON_PLACE
 */
object NumberPlace {
    const val CATEGORY_PLACE = 1000
    const val MAIN_PLACE = 10
    const val WEAPON_PLACE = 1
}