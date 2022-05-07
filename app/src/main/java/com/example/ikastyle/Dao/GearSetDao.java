package com.example.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ikastyle.Entity.GearSet;

import java.util.List;

@Dao
public interface GearSetDao {
    @Insert
    public void InsertGearSet(GearSet gearSet);

    @Query("SELECT * FROM TRAN_GEAR_SET WHERE category_id = :categoryId AND main_id = :mainId AND customization_id = :customizationId ORDER BY update_date DESC")
    public List<GearSet> getGearSetList(int categoryId, int mainId, int customizationId);
}
