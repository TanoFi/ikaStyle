package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "MAST_MAIN_CATEGORY", primaryKeys = {"id","language_code"})
public class MainCategory {
    @ColumnInfo(name = "id")
    @NonNull
    public int id;

    @ColumnInfo(name = "language_code")
    @NonNull
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;
}
