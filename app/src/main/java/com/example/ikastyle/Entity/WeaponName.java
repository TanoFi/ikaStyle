package com.example.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "MAST_WEAPON_NAME", primaryKeys = {"id", "main_id", "category_id", "language_code"},
        foreignKeys = {@ForeignKey(entity = MainName.class, parentColumns = "id", childColumns = "main_id"),
                       @ForeignKey(entity = MainCategory.class, parentColumns = "id", childColumns = "category_id")}
)
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
}
