package com.example.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.WeaponMainCategoryDao;
import com.example.ikastyle.DatabaseView.WeaponMainCategory;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.Entity.MainName;
import com.example.ikastyle.Entity.WeaponName;

@Database(entities = {MainCategory.class, MainName.class, WeaponName.class}, views = {WeaponMainCategory.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();
    public abstract WeaponMainCategoryDao weaponMainCategoryDao();

    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context, String databaseName, String databaseFileName){
        if(instance != null)
        {
            return  instance;
        }
        instance = Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .createFromAsset(databaseFileName)
                .build();

        return instance;
    }
}
