package com.splatool.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.splatool.ikastyle.Entity.ShoesGear;

import java.util.List;

@Dao
public interface ShoesGearDao {
    @Query("SELECT * FROM MAST_SHOES_GEAR WHERE language_code = :languageCode ORDER BY name")
    List<ShoesGear> getShoesGearList(int languageCode);
}
