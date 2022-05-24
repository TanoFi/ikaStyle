package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import androidx.room.*

/*
 * WEAPON_MAINビューのDAO
 */
@Dao
interface CustomizationMainDao {
    @Query("SELECT * FROM CUSTOMIZATION_MAIN_NAME WHERE language_code = :languageCode ORDER BY category_id, main_name, weapon_id")
    fun getWeaponMainList(languageCode: Int): List<CustomizationMain>

    @Query("SELECT * FROM CUSTOMIZATION_MAIN_NAME WHERE language_code = :languageCode AND category_id = :categoryId ORDER BY category_id, main_name, weapon_id")
    fun getWeaponMainListByCategory(languageCode: Int, categoryId : Int): List<CustomizationMain>
}