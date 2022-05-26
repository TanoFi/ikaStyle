package com.splatool.ikastyle.dao

import com.splatool.ikastyle.entity.MainCategory
import androidx.room.*

@Dao
interface MainCategoryDao {
    @Query("SELECT * FROM MAST_MAIN_CATEGORY WHERE language_code = :languageCode ORDER BY id")
    open fun getMainCategoryList(languageCode: Int): List<MainCategory>
}