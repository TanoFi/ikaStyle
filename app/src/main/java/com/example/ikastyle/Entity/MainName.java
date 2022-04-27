package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Relation;


@Entity(tableName = "MAST_MAIN_NAME", primaryKeys = {"id", "category_id", "language_code"})
public class MainName {
    @ColumnInfo(name = "id")
    @NonNull
    public int id;

    @ColumnInfo(name = "category_id")
    @NonNull
    public int categoryId;

    @ColumnInfo(name = "language_code")
    @NonNull
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;
}
