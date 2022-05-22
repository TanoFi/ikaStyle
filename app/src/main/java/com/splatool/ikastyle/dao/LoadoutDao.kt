package com.splatool.ikastyle.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.splatool.ikastyle.Entity.Loadout;

import java.util.List;

@Dao
public interface LoadoutDao {
    @Insert
    void InsertLoadout(Loadout loadout);

    @Query("SELECT * FROM TRAN_LOADOUT WHERE category_id = :categoryId AND main_id = :mainId AND (customization_id = :customizationId OR customization_id = 0) ORDER BY update_date DESC")
    List<Loadout> getLoadoutList(int categoryId, int mainId, int customizationId);

    @Delete
    void DeleteLoadout(Loadout loadout);
}
