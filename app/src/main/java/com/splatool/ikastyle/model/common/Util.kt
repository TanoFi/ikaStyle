package com.splatool.ikastyle.model.common

import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.const.NumberPlace
import com.splatool.ikastyle.model.common.const.ResourceIdMap
import android.graphics.Color
import java.util.*

object Util {
    /*
     * 端末の設定が日本語なら2、それ以外なら1を返す
     */
    fun getLanguageCode(): Int {
        val local = Locale.getDefault()
        return if (local == Locale.JAPAN) {
            2
        } else {
            1
        }
    }

    /*
     * ブキのカテゴリーID,メインID,カスタマイズIDから絶対IDを返す
     */
    fun getAbsoluteId(categoryId: Int, mainId: Int, customizationId: Int): Int {
        return categoryId * NumberPlace.CATEGORY_PLACE + mainId * NumberPlace.MAIN_PLACE + customizationId * NumberPlace.WEAPON_PLACE
    }

    /*
     * ブキの絶対IDを受け取ってカテゴリーIDを返す
     */
    fun getCategoryId(absoluteId: Int): Int {
        return absoluteId / NumberPlace.CATEGORY_PLACE
    }

    /*
     * ブキの絶対IDを受け取ってメインIDを返す
     */
    fun getMainId(absoluteId: Int): Int {
        return absoluteId % NumberPlace.CATEGORY_PLACE / NumberPlace.MAIN_PLACE
    }

    /*
     * ブキの絶対IDを受け取ってカスタマイズIDを返す
     */
    fun getCustomizationId(absoluteId: Int): Int {
        return absoluteId % NumberPlace.CATEGORY_PLACE % NumberPlace.MAIN_PLACE
    }

    /*
     * ギアパワーのIDを受け取って対応する画像のResourceIdを返す
     */
    fun getGearPowerResourceId(gearPowerId: Int): Int {
        return Optional.ofNullable(ResourceIdMap.gearPowerResourceIdMap[gearPowerId]).orElse(0)
    }

    /*
     * アタマのギアIDを受け取って対応する画像のResourceIdを返す
     */
    fun getHeadGearResourceId(gearId: Int): Int {
        return Optional.ofNullable(ResourceIdMap.headGearResourceIdMap[gearId])
            .orElse(R.drawable.headgear0)
    }

    /*
     * フクのギアIDを受け取って対応する画像のResourceIdを返す
     */
    fun getClothingResourceId(gearId: Int): Int {
        return Optional.ofNullable(ResourceIdMap.clothingGearResourceIdMap[gearId])
            .orElse(R.drawable.clothing_gear0)
    }

    /*
     * クツのギアIDを受け取って対応する画像のResourceIdを返す
     */
    fun getShoesResourceId(gearId: Int): Int {
        return Optional.ofNullable(ResourceIdMap.shoesGearResourceIdMap[gearId])
            .orElse(R.drawable.shoes_gear0)
    }

    /*
     * ブキの絶対IDを受け取って対応する画像のResourceIdを返す
     */
    fun getWeaponResourceId(absoluteId: Int): Int {
        return Optional.ofNullable(ResourceIdMap.weaponResourceIdMap[absoluteId])
            .orElse(R.drawable.weapon1011_sploosh_o_matic)
    }

    /*
     * ランダムな色を返す
     */
    fun getRandomColor(): Int {
        val rgbNum = 256
        val random = Random()
        val red = random.nextInt(rgbNum)
        val green = random.nextInt(rgbNum)
        val blue = random.nextInt(rgbNum)
        return Color.rgb(red, green, blue)
    }
}