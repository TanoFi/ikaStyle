package com.splatool.ikastyle.model.data.dao

import com.splatool.ikastyle.model.data.entity.HeadGear
import androidx.room.*

@Dao
interface HeadGearDao {
    @Query("SELECT * FROM MAST_HEAD_GEAR WHERE language_code = :languageCode ORDER BY name")
    suspend fun getHeadGearList(languageCode: Int): List<HeadGear>
}