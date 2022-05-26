package com.splatool.ikastyle.dao

import com.splatool.ikastyle.databaseView.CustomizationMain
import androidx.room.*

/*
 * WEAPON_MAINビューのDAO
 */
@Dao
interface CustomizationMainDao {
    @Query("SELECT * FROM CUSTOMIZATION_MAIN_NAME WHERE language_code = :languageCode ORDER BY category_id, main_name, weapon_id")
    fun getWeaponMainList(languageCode: Int): List<CustomizationMain>
}