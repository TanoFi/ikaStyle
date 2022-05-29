package com.splatool.ikastyle.model.data.repository

import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.model.data.dao.LoadoutDao
import com.splatool.ikastyle.model.data.entity.Loadout

class LoadoutRepository(private val loadoutDao: LoadoutDao) {
    suspend fun getLoadoutList(absoluteWeaponId : Int): ArrayList<Loadout>{
        val categoryId = Util.getCategoryId(absoluteWeaponId)
        val mainId = Util.getMainId(absoluteWeaponId)
        val customizationId = Util.getCustomizationId(absoluteWeaponId)

        return loadoutDao.getLoadoutList(categoryId, mainId, customizationId).toMutableList() as ArrayList<Loadout>
    }

    suspend fun deleteLoadout(loadoutId : Int){
        loadoutDao.deleteLoadout(loadoutId)
    }

    suspend fun saveLoadout(loadout: Loadout){
        loadoutDao.insertLoadout(loadout)
    }
}