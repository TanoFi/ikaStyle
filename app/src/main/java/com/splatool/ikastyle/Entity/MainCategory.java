package com.splatool.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.splatool.ikastyle.Common.Util;

@Entity(tableName = "MAST_MAIN_CATEGORY", primaryKeys = {"id","language_code"})
public class MainCategory {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    public int getAbsoluteId(){
        return Util.getAbsoluteId(id, 0, 0);
    }

    @NonNull
    public String getName(){
        return name;
    }
}
