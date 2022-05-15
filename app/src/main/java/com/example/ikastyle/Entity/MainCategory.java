package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

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
        return id * 1000;
    }

    public String getName(){
        return name;
    }
}
