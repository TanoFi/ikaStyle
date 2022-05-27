package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.model.common.const.NumberPlace
import com.splatool.ikastyle.model.data.dao.CustomizationMainDao
import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class CustomizationMainRepository(private val customizationMainDao: CustomizationMainDao) {
    private val languageCode = Util.getLanguageCode()

    suspend fun getWeapon() : ArrayList<Pair<Int, String>>{
        val weaponList = customizationMainDao.getWeaponMainList(languageCode)
        return convertWeaponToPair(weaponList)
    }

    suspend fun getWeaponByCategory(categoryId : Int) : ArrayList<Pair<Int, String>>{
        val weaponList = customizationMainDao.getWeaponMainListByCategory(languageCode, categoryId)
        return convertWeaponToPair(weaponList)
    }


    private fun convertWeaponToPair(weaponList: List<CustomizationMain>) : ArrayList<Pair<Int, String>>{
        val weaponPairList : ArrayList<Pair<Int, String>> = arrayListOf()

        // (メインID, そのメインを持ったブキのリスト) でMapを作成
        val customizationMainMap = TreeMap(weaponList.stream().collect(
            Collectors.groupingBy { x: CustomizationMain -> x.categoryId * NumberPlace.CATEGORY_PLACE + x.mainId * NumberPlace.MAIN_PLACE }
        ))

        for ((key, value) in customizationMainMap) {
            // メインのデータをAdd
            weaponPairList.add(Pair(key, value[0].mainName))
            // カスタマイズのデータをAdd
            value.forEach{
                weaponPairList.add(Pair(it.getAbsoluteId(), it.weaponName))
            }
        }

        return weaponPairList
    }
}