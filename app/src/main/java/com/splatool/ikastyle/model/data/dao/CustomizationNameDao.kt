package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.CustomizationName
import androidx.room.*

@Dao
interface CustomizationNameDao {
    @Query("SELECT * FROM MAST_CUSTOMIZATION_NAME WHERE language_code = :language_code ORDER BY category_id, main_id, id")
    suspend fun getWeaponNameList(language_code: Int): List<CustomizationName>
}