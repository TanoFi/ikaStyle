package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ikastyle.Entity.MainCategory;

import java.util.List;

@Dao
public interface MainCategoryDao {
    @Insert
    void insertAll(MainCategory... mainCategoryEntities);

    @Query("SELECT name FROM main_category WHERE language_code = :languageCode")
    public  List<String> mainCategoryNames(int languageCode);
}
