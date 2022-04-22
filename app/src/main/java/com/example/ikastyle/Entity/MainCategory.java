package com.example.ikastyle.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "main_category", primaryKeys = {"id","language_code"})
public class MainCategory {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "language_code")
    public int languageCode;

    @ColumnInfo(name = "name")
    public String name;

    //あとで消す : 初期データ投入のInsertにしか使わなさそうだから
    public MainCategory(int id, int languageCode, String name){
        this.id = id;
        this.languageCode = languageCode;
        this.name = name;
    }
}
