package com.splatool.ikastyle.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "MAST_CLOTHING_GEAR", primaryKeys = ["id", "language_code"])
data class ClothingGear (
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "language_code") val languageCode: Int,
    @ColumnInfo(name = "name") @NonNull val name: String
)