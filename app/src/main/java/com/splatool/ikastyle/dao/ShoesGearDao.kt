package com.splatool.ikastyle.dao

import com.splatool.ikastyle.entity.ShoesGear
import androidx.room.*

@Dao
interface ShoesGearDao {
    @Query("SELECT * FROM MAST_SHOES_GEAR WHERE language_code = :languageCode ORDER BY name")
    fun getShoesGearList(languageCode: Int): List<ShoesGear>
}