package com.splatool.ikastyle.model.data.entity

import androidx.annotation.NonNull
import androidx.room.*
import com.splatool.ikastyle.common.Util

@Entity(tableName = "TRAN_LOADOUT")
data class Loadout
    (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id : Int = 0,
    @ColumnInfo(name = "name") @NonNull var name: String = "",
    @ColumnInfo(name = "category_id") @NonNull var categoryId: Int = 0,
    @ColumnInfo(name = "main_id") @NonNull var mainId: Int = 0,
    @ColumnInfo(name = "customization_id") @NonNull var customizationId: Int = 0,
    @ColumnInfo(name = "head_gear_id") @NonNull var headGearId: Int = 0,
    @ColumnInfo(name = "head_main") @NonNull var headMain: Int = 0,
    @ColumnInfo(name = "head_sub1") @NonNull var headSub1: Int = 0,
    @ColumnInfo(name = "head_sub2") @NonNull var headSub2: Int = 0,
    @ColumnInfo(name = "head_sub3") @NonNull var headSub3: Int = 0,
    @ColumnInfo(name = "clothing_gear_id") @NonNull var clothingGearId: Int = 0,
    @ColumnInfo(name = "clothing_main") @NonNull var clothingMain: Int = 0,
    @ColumnInfo(name = "clothing_sub1") @NonNull var clothingSub1: Int = 0,
    @ColumnInfo(name = "clothing_sub2") @NonNull var clothingSub2: Int = 0,
    @ColumnInfo(name = "clothing_sub3") @NonNull var clothingSub3: Int = 0,
    @ColumnInfo(name = "shoes_gear_id") @NonNull var shoesGearId: Int = 0,
    @ColumnInfo(name = "shoes_main") @NonNull var shoesMain: Int = 0,
    @ColumnInfo(name = "shoes_sub1") @NonNull var shoesSub1: Int = 0,
    @ColumnInfo(name = "shoes_sub2") @NonNull var shoesSub2: Int = 0,
    @ColumnInfo(name = "shoes_sub3") @NonNull var shoesSub3: Int = 0,
    @ColumnInfo(name = "update_date") @NonNull var updateDate: Long = 0,
){
    @Ignore var headMainResourceId : Int = 0
        get() {
            return Util.getGearPowerResourceId(headMain)
        }
    @Ignore var headSub1ResourceId : Int = 0
        get() {
            return Util.getGearPowerResourceId(headSub1)
        }
    @Ignore var headSub2ResourceId : Int = 0
        get() {
            return Util.getGearPowerResourceId(headSub2)
        }
    @Ignore var headSub3ResourceId : Int = Util.getGearPowerResourceId(headSub3)
        get() {
            return Util.getGearPowerResourceId(headSub3)
        }
    @Ignore var clothingMainResourceId : Int = Util.getGearPowerResourceId(clothingMain)
        get() {
            return Util.getGearPowerResourceId(clothingMain)
        }
    @Ignore var clothingSub1ResourceId : Int = Util.getGearPowerResourceId(clothingSub1)
        get() {
            return Util.getGearPowerResourceId(clothingSub1)
        }
    @Ignore var clothingSub2ResourceId : Int = Util.getGearPowerResourceId(clothingSub2)
        get() {
            return Util.getGearPowerResourceId(clothingSub2)
        }
    @Ignore var clothingSub3ResourceId : Int = Util.getGearPowerResourceId(clothingSub3)
        get() {
            return Util.getGearPowerResourceId(clothingSub3)
        }
    @Ignore var shoesMainResourceId : Int = Util.getGearPowerResourceId(shoesMain)
        get() {
            return Util.getGearPowerResourceId(shoesMain)
        }
    @Ignore var shoesSub1ResourceId : Int = Util.getGearPowerResourceId(shoesSub1)
        get() {
            return Util.getGearPowerResourceId(shoesSub1)
        }
    @Ignore var shoesSub2ResourceId : Int = Util.getGearPowerResourceId(shoesSub2)
        get() {
            return Util.getGearPowerResourceId(shoesSub2)
        }
    @Ignore var shoesSub3ResourceId : Int = Util.getGearPowerResourceId(shoesSub3)
        get() {
            return Util.getGearPowerResourceId(shoesSub3)
        }
    }