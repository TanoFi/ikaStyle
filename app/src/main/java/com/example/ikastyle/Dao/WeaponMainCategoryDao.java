package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.DatabaseView.WeaponMainCategory;

import java.util.List;

@Dao
public interface WeaponMainCategoryDao {

    // なぜかビュー名やカラム名が赤字になってしまうがデータ取得はできる
    @Query("SELECT * FROM WEAPON_MAIN_CATEGORY_NAME WHERE language_code = :languageCode ORDER BY category_id, main_id, weapon_id")
    public List<WeaponMainCategory> mainWeaponNameList(int languageCode);
}
