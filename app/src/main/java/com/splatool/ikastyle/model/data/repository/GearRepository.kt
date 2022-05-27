package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.model.data.dao.ClothingGearDao
import com.splatool.ikastyle.model.data.dao.HeadGearDao
import com.splatool.ikastyle.model.data.dao.ShoesGearDao
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.ShoesGear

class GearRepository(private val headGearDao: HeadGearDao, private val clothingGearDao: ClothingGearDao, private val shoesGearDao: ShoesGearDao) {
    val languageCode = Util.getLanguageCode()

    suspend fun getHeadGearList() :  ArrayList<HeadGear>{
        return headGearDao.getHeadGearList(languageCode).toMutableList() as ArrayList<HeadGear>
    }

    suspend fun getClothingGear() : ArrayList<ClothingGear>{
        return clothingGearDao.getClothingGearList(languageCode).toMutableList() as ArrayList<ClothingGear>
    }

    suspend fun getShoesGear() : ArrayList<ShoesGear>{
        return shoesGearDao.getShoesGearList(languageCode).toMutableList() as ArrayList<ShoesGear>
    }
}