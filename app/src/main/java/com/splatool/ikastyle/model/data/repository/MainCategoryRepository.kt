package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.model.data.dao.MainCategoryDao
import com.splatool.ikastyle.model.data.entity.MainCategory

class MainCategoryRepository(private val categoryDao : MainCategoryDao) {
    val languageCode = Util.getLanguageCode()

    suspend fun getCategoryList(): ArrayList<Pair<Int, String>> {
        val categoryList = categoryDao.getMainCategoryList(languageCode)
        return convertCategoryToPair(categoryList)
    }

    private fun convertCategoryToPair(categoryList: List<MainCategory>) : ArrayList<Pair<Int, String>>{
        val categoryPairList : ArrayList<Pair<Int, String>> = arrayListOf()
        categoryList.forEach{
            categoryPairList.add(Pair(it.getAbsoluteId(), it.name))
        }
        return categoryPairList
    }
}