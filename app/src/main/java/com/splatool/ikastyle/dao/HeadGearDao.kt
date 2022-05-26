package com.splatool.ikastyle.dao

import com.splatool.ikastyle.entity.HeadGear
import androidx.room.*

@Dao
interface HeadGearDao {
    @Query("SELECT * FROM MAST_HEAD_GEAR WHERE language_code = :languageCode ORDER BY name")
    fun getHeadGearList(languageCode: Int): List<HeadGear>
}