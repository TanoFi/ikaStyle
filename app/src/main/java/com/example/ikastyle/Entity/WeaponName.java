package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.ikastyle.Common.Const.NumberPlace;

@Entity(tableName = "MAST_WEAPON_NAME", primaryKeys = {"id", "main_id", "category_id", "language_code"})
public class WeaponName {
    @ColumnInfo(name = "id")
    @NonNull
    public int id;

    @ColumnInfo(name = "main_id")
    @NonNull
    public int mainId;

    @ColumnInfo(name = "category_id")
    @NonNull
    public int categoryId;

    @ColumnInfo(name = "language_code")
    @NonNull
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    public String getName(){
        return name;
    }

    public int getAbsoluteId(){
        return categoryId * NumberPlace.CATEGORY_PLACE + mainId * NumberPlace.MAIN_PLACE + id;
    }
}
