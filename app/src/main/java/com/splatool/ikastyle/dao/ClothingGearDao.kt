package com.splatool.ikastyle.dao

import com.splatool.ikastyle.entity.ClothingGear
import androidx.room.*

@Dao
interface ClothingGearDao {
    @Query("SELECT * FROM MAST_CLOTHING_GEAR WHERE language_code = :languageCode ORDER BY name")
    fun getClothingGearList(languageCode: Int): List<ClothingGear>
}