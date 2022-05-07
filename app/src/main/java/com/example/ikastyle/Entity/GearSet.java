package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "TRAN_GEAR_SET")
public class GearSet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "main_id")
    public int mainId;

    @ColumnInfo(name = "customization_id")
    public int customizationId;

    @ColumnInfo(name = "head_gear_id")
    public int headGearId;

    @ColumnInfo(name = "head_main")
    public int headMain;

    @ColumnInfo(name = "head_sub1")
    public int headSub1;

    @ColumnInfo(name = "head_sub2")
    public int headSub2;

    @ColumnInfo(name = "head_sub3")
    public int headSub3;

    @ColumnInfo(name = "clothing_gear_id")
    public int clothingGearId;

    @ColumnInfo(name = "clothing_main")
    public int clothingMain;

    @ColumnInfo(name = "clothing_sub1")
    public int clothingSub1;

    @ColumnInfo(name = "clothing_sub2")
    public int clothingSub2;

    @ColumnInfo(name = "clothing_sub3")
    public int clothingSub3;

    @ColumnInfo(name = "shoes_gear_id")
    public int shoesGearId;

    @ColumnInfo(name = "shoes_main")
    public int shoesMain;

    @ColumnInfo(name = "shoes_sub1")
    public int shoesSub1;

    @ColumnInfo(name = "shoes_sub2")
    public int shoesSub2;

    @ColumnInfo(name = "shoes_sub3")
    public int shoesSub3;

    @ColumnInfo(name = "update_date")
    public Long updateDate;

    public GearSet(String name,
                   int categoryId, int mainId, int customizationId,
                   int headGearId, int headMain, int headSub1, int headSub2, int headSub3,
                   int clothingGearId, int clothingMain, int clothingSub1, int clothingSub2, int clothingSub3,
                   int shoesGearId, int shoesMain, int shoesSub1, int shoesSub2, int shoesSub3,
                   Long updateDate){
        this.id = 0; // autoGenerateがtrueなので0でInsertすればいいらしい
        this.name = name;
        this.categoryId = categoryId;
        this.mainId = mainId;
        this.customizationId = customizationId;
        this.headGearId = headGearId;
        this.headMain = headMain;
        this.headSub1 = headSub1;
        this.headSub2 = headSub2;
        this.headSub3 = headSub3;
        this.clothingGearId = clothingGearId;
        this.clothingMain = clothingMain;
        this.clothingSub1 = clothingSub1;
        this.clothingSub2 = clothingSub2;
        this.clothingSub3 = clothingSub3;
        this.shoesGearId = shoesGearId;
        this.shoesMain = shoesMain;
        this.shoesSub1 = shoesSub1;
        this.shoesSub2 = shoesSub2;
        this.shoesSub3 = shoesSub3;
        this.updateDate = updateDate;
    }

}
