package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.CustomizationName
import androidx.room.*

@Dao
interface CustomizationNameDao {
    @Query("SELECT * FROM MAST_CUSTOMIZATION_NAME WHERE language_code = :language_code ORDER BY category_id, main_id, id")
    suspend fun getWeaponNameList(language_code: Int): List<CustomizationName>

    @Query("SELECT * FROM MAST_CUSTOMIZATION_NAME WHERE language_code = :language_code AND category_id = :category_id ORDER BY category_id, main_id, id")
    suspend fun getWeaponNameListByCategory(language_code: Int, category_id :Int): List<CustomizationName>
}