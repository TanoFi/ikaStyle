package com.example.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Entity.MainCategory;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {MainCategory.class}, version = 1)
public abstract class MainCategoryDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();

    private static MainCategoryDatabase instance = null;

    public static MainCategoryDatabase getDatabase(Context context, String databaseName){
        if(instance != null)
        {
            return  instance;
        }
        instance = Room.databaseBuilder(context, MainCategoryDatabase.class, databaseName)
                .createFromAsset("database/MastMainCategory.db")
                .build();

        return instance;
    }
}
