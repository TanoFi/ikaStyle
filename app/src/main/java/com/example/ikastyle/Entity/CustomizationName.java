package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.ikastyle.Common.Util;

@Entity(tableName = "MAST_CUSTOMIZATION_NAME", primaryKeys = {"id", "main_id", "category_id", "language_code"})
public class CustomizationName {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "main_id")
    public int mainId;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @NonNull
    public String getName(){
        return name;
    }

    public int getAbsoluteId(){
        return Util.getAbsoluteId(categoryId, mainId, id);
    }
}
