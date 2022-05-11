package com.example.ikastyle.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "MAST_HEAD_GEAR", primaryKeys = {"id", "language_code"})
public class HeadGear {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "name")
    public String name;
}
