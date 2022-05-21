package com.splatool.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.splatool.ikastyle.Entity.MainCategory;

import java.util.List;

@Dao
public interface MainCategoryDao {
    @Query("SELECT * FROM MAST_MAIN_CATEGORY WHERE language_code = :languageCode ORDER BY id")
    List<MainCategory> getMainCategoryList(int languageCode);
}