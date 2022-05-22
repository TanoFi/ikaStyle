package com.splatool.ikastyle.dao

import com.splatool.ikastyle.entity.CustomizationName
import androidx.room.*

@Dao
interface CustomizationNameDao {
    @Query("SELECT * FROM MAST_CUSTOMIZATION_NAME WHERE language_code = :language_code ORDER BY category_id, main_id, id")
    fun getWeaponNameList(language_code: Int): List<CustomizationName>
}