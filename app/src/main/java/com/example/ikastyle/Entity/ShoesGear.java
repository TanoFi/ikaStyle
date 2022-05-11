package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "MAST_SHOES_GEAR", primaryKeys = {"id", "language_code"})
public class ShoesGear {
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
