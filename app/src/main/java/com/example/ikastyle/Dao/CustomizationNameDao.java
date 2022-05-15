package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.Entity.CustomizationName;

import java.util.List;

@Dao
public interface CustomizationNameDao {
    @Query("SELECT * FROM MAST_CUSTOMIZATION_NAME WHERE language_code = :language_code ORDER BY category_id, main_id, id")
    List<CustomizationName> getWeaponNameList(int language_code);
}
