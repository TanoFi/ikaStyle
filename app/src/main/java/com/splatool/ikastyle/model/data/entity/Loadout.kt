package com.splatool.ikastyle.model.data.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "TRAN_LOADOUT")
data class Loadout
    (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "name") @NonNull val name: String,
    @ColumnInfo(name = "category_id") @NonNull val categoryId: Int,
    @ColumnInfo(name = "main_id") @NonNull val mainId: Int,
    @ColumnInfo(name = "customization_id") @NonNull val customizationId: Int,
    @ColumnInfo(name = "head_gear_id") @NonNull var headGearId: Int,
    @ColumnInfo(name = "head_main") @NonNull var headMain: Int,
    @ColumnInfo(name = "head_sub1") @NonNull var headSub1: Int,
    @ColumnInfo(name = "head_sub2") @NonNull var headSub2: Int,
    @ColumnInfo(name = "head_sub3") @NonNull var headSub3: Int,
    @ColumnInfo(name = "clothing_gear_id") @NonNull var clothingGearId: Int,
    @ColumnInfo(name = "clothing_main") @NonNull var clothingMain: Int,
    @ColumnInfo(name = "clothing_sub1") @NonNull var clothingSub1: Int,
    @ColumnInfo(name = "clothing_sub2") @NonNull var clothingSub2: Int,
    @ColumnInfo(name = "clothing_sub3") @NonNull var clothingSub3: Int,
    @ColumnInfo(name = "shoes_gear_id") @NonNull var shoesGearId: Int,
    @ColumnInfo(name = "shoes_main") @NonNull var shoesMain: Int,
    @ColumnInfo(name = "shoes_sub1") @NonNull var shoesSub1: Int,
    @ColumnInfo(name = "shoes_sub2") @NonNull var shoesSub2: Int,
    @ColumnInfo(name = "shoes_sub3") @NonNull var shoesSub3: Int,
    @ColumnInfo(name = "update_date") @NonNull var updateDate: Long
)