package com.splatool.ikastyle.model.data.entity

import androidx.annotation.NonNull
import androidx.room.*
import com.splatool.ikastyle.model.common.Util

@Entity(
    tableName = "MAST_CUSTOMIZATION_NAME",
    primaryKeys = ["id", "main_id", "category_id", "language_code"]
)
data class CustomizationName (
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "main_id") val mainId : Int,
    @ColumnInfo(name = "category_id") val categoryId : Int,
    @ColumnInfo(name = "language_code") val languageCode : Int,
    @ColumnInfo(name = "name") @NonNull val name: String
) {
    fun getAbsoluteId(): Int {
        return Util.getAbsoluteId(categoryId, mainId, id)
    }
}
