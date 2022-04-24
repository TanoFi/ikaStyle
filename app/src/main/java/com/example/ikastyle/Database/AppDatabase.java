package com.example.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Entity.MainCategory;

@Database(entities = {MainCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();

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
