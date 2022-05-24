package com.splatool.ikastyle.model.data.entity

import androidx.annotation.NonNull
import androidx.room.*
import com.splatool.ikastyle.common.Util

@Entity(tableName = "MAST_MAIN_CATEGORY", primaryKeys = ["id", "language_code"])
data class MainCategory (
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "language_code") val languageCode : Int,
    @ColumnInfo(name = "name") @NonNull val name: String
){
    fun getAbsoluteId(): Int {
        return Util.getAbsoluteId(id, 0, 0)
    }
}