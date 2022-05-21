package com.splatool.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.splatool.ikastyle.Common.Util;


@Entity(tableName = "MAST_MAIN_NAME", primaryKeys = {"id", "category_id", "language_code"})
public class MainName {
    @ColumnInfo(name = "id")
    public int id;

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
        return Util.getAbsoluteId(categoryId, id, 0);
    }
}