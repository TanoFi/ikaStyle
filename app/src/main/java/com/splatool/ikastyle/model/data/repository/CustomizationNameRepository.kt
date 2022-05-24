package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.model.data.dao.CustomizationNameDao
import com.splatool.ikastyle.model.data.entity.CustomizationName

class CustomizationNameRepository(private val customizationDao : CustomizationNameDao) {
    val languageCode = Util.getLanguageCode()

    suspend fun getCustomizationList(): List<CustomizationName> {
        return customizationDao.getWeaponNameList(languageCode)
    }
}