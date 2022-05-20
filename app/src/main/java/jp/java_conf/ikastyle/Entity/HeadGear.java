package jp.java_conf.ikastyle.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "MAST_HEAD_GEAR", primaryKeys = {"id", "language_code"})
public class HeadGear {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;
}
