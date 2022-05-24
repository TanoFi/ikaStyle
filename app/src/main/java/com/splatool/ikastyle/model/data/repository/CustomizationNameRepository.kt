package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.model.data.dao.CustomizationNameDao
import com.splatool.ikastyle.model.data.entity.CustomizationName
import com.splatool.ikastyle.model.data.entity.MainCategory

class CustomizationNameRepository(private val customizationDao : CustomizationNameDao) {
    val languageCode = Util.getLanguageCode()

    suspend fun getCustomizationList(): ArrayList<Pair<Int, String>> {
        val customizationList = customizationDao.getWeaponNameList(languageCode)
        return convertCustomizationToPair(customizationList)
    }

    suspend fun getCustomizationListByCategory(categoryId : Int) : ArrayList<Pair<Int, String>>{
        val customizationList = customizationDao.getWeaponNameListByCategory(languageCode, categoryId)
        return convertCustomizationToPair(customizationList)
    }

    private fun convertCustomizationToPair(customizationList: List<CustomizationName>) : ArrayList<Pair<Int, String>>{
        val customizationPairList : ArrayList<Pair<Int, String>> = arrayListOf()
        customizationList.forEach{
            customizationPairList.add(Pair(it.getAbsoluteId(), it.name))
        }
        return customizationPairList
    }
}