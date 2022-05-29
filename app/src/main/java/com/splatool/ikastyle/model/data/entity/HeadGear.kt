package com.splatool.ikastyle.model.data.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "MAST_HEAD_GEAR", primaryKeys = ["id", "language_code"])
data class HeadGear (
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "language_code") val languageCode : Int,
    @ColumnInfo(name = "name") @NonNull val name: String
)