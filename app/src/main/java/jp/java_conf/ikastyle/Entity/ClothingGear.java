package jp.java_conf.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "MAST_CLOTHING_GEAR", primaryKeys = {"id", "language_code"})
public class ClothingGear {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;
}