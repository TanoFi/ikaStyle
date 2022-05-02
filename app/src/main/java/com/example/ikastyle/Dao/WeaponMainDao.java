package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.DatabaseView.WeaponMain;

import java.util.List;

/*
 * WEAPON_MAINビューのDAO
 */
@Dao
public interface WeaponMainDao {
    @Query("SELECT * FROM WEAPON_MAIN_NAME WHERE language_code = :languageCode ORDER BY category_id, main_name, weapon_id")
    public List<WeaponMain> getWeaponMainList(int languageCode);
}
