package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.ClothingGear
import androidx.room.*

@Dao
interface ClothingGearDao {
    @Query("SELECT * FROM MAST_CLOTHING_GEAR WHERE language_code = :languageCode ORDER BY name")
    suspend fun getClothingGearList(languageCode: Int): List<ClothingGear>
}