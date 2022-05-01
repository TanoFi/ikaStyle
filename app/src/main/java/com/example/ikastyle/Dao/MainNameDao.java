package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ikastyle.Entity.MainName;

import java.util.List;

@Dao
public interface MainNameDao {
    @Query("SELECT * FROM MAST_MAIN_NAME WHERE language_code = :language_code ORDER BY category_id, id")
    public List<MainName> getMainNameList(int language_code);
}
