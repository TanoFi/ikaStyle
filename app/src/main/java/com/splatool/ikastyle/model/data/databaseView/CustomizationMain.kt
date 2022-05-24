package com.splatool.ikastyle.model.data.databaseView

import com.splatool.ikastyle.common.const.NumberPlace
import androidx.room.ColumnInfo
import androidx.room.DatabaseView

/*
 * MAST_MAIN_NAMEとMAST_CUSTOMIZATION_NAMEの結合テーブルを取得するView
 */
@DatabaseView(
    viewName = "CUSTOMIZATION_MAIN_NAME", value = """SELECT 
WN.category_id,
WN.main_id,
WN.id as weapon_id,
WN.language_code, 
MN.name as main_name,
WN.name as weapon_name
FROM MAST_MAIN_NAME MN
INNER JOIN MAST_CUSTOMIZATION_NAME WN
ON WN.category_id = MN.category_id
AND WN.main_id = MN.id 
AND WN.language_code = MN.language_code"""
)
data class CustomizationMain (
    @ColumnInfo(name = "category_id") val categoryId : Int,
    @ColumnInfo(name = "main_id") val mainId : Int,
    @ColumnInfo(name = "weapon_id") val weaponId : Int,
    @ColumnInfo(name = "language_code") val languageCode : Int,
    @ColumnInfo(name = "main_name") val mainName: String,
    @ColumnInfo(name = "weapon_name") val weaponName: String
){
    fun getAbsoluteId(): Int {
        return categoryId * NumberPlace.CATEGORY_PLACE + mainId * NumberPlace.MAIN_PLACE + weaponId
    }
}