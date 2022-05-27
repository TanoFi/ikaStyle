package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.ShoesGear
import androidx.room.*

@Dao
interface ShoesGearDao {
    @Query("SELECT * FROM MAST_SHOES_GEAR WHERE language_code = :languageCode ORDER BY name")
    suspend fun getShoesGearList(languageCode: Int): List<ShoesGear>
}