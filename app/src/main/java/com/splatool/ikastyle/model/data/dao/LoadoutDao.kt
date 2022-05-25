package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.Loadout
import androidx.room.*

@Dao
interface LoadoutDao {
    @Insert
    fun insertLoadout(loadout: Loadout)
    @Query("SELECT * FROM TRAN_LOADOUT WHERE category_id = :categoryId AND main_id = :mainId AND (customization_id = :customizationId OR customization_id = 0) ORDER BY update_date DESC")
    suspend fun getLoadoutList(
        categoryId: Int,
        mainId: Int,
        customizationId: Int
    ): List<Loadout>

    @Query("DELETE FROM TRAN_LOADOUT WHERE id = :loadoutId")
    suspend fun deleteLoadout(loadoutId: Int)
}