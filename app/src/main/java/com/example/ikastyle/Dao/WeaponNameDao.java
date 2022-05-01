package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.Entity.WeaponName;

import java.util.List;

@Dao
public interface WeaponNameDao {
    @Query("SELECT * FROM MAST_WEAPON_NAME WHERE language_code = :language_code ORDER BY category_id, main_id, id")
    public List<WeaponName> getWeaponNameList(int language_code);
}
