package com.example.ikastyle.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Entity.MainCategory;

@Database(entities = {MainCategory.class}, version = 1)
public abstract class MainCategoryDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();
}
