package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.DatabaseView.WeaponMainCategory;

import java.util.List;

@Dao
public interface WeaponMainCategoryDao {

    @Query("SELECT * FROM MAST_MAIN_CATEGORY WHERE language_code = :languageCode ORDER BY ")
    public List<WeaponMainCategory> mainWeaponNameList(int languageCode);
}
