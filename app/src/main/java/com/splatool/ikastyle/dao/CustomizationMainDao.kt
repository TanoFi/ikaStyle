package com.splatool.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.splatool.ikastyle.DatabaseView.CustomizationMain;

import java.util.List;

/*
 * WEAPON_MAINビューのDAO
 */
@Dao
public interface CustomizationMainDao {
    @Query("SELECT * FROM CUSTOMIZATION_MAIN_NAME WHERE language_code = :languageCode ORDER BY category_id, main_name, weapon_id")
    List<CustomizationMain> getWeaponMainList(int languageCode);
}
