package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.model.data.dao.MainCategoryDao
import com.splatool.ikastyle.model.data.entity.MainCategory

class MainCategoryRepository(private val categoryDao : MainCategoryDao) {
    val languageCode = Util.getLanguageCode()

    suspend fun getCategoryList(): List<MainCategory> {
        return categoryDao.getMainCategoryList(languageCode)
    }
}