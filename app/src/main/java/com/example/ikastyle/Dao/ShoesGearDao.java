package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.Entity.HeadGear;
import com.example.ikastyle.Entity.ShoesGear;

import java.util.List;

@Dao
public interface ShoesGearDao {
    @Query("SELECT * FROM MAST_SHOES_GEAR WHERE language_code = :languageCode ORDER BY name")
    public List<ShoesGear> getShoesGearList(int languageCode);
}
