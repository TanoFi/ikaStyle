package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ikastyle.Entity.ClothingGear;
import com.example.ikastyle.Entity.HeadGear;

import java.util.List;

@Dao
public interface ClothingGearDao {
    @Query("SELECT * FROM MAST_CLOTHING_GEAR WHERE language_code = :languageCode ORDER BY name")
    public List<ClothingGear> getClothingGearList(int languageCode);
}
