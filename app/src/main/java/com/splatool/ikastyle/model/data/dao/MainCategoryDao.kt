package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.MainCategory
import androidx.room.*

@Dao
interface MainCategoryDao {
    @Query("SELECT * FROM MAST_MAIN_CATEGORY WHERE language_code = :languageCode ORDER BY id")
    suspend fun getMainCategoryList(languageCode: Int): List<MainCategory>


    @Query("SELECT * FROM MAST_MAIN_CATEGORY WHERE language_code = :languageCode ORDER BY id")
    fun getMainCategoryList_nonSuspend(languageCode: Int): List<MainCategory>
}