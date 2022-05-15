package com.example.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ikastyle.Common.Const.DatabaseName;
import com.example.ikastyle.Dao.ClothingGearDao;
import com.example.ikastyle.Dao.GearSetDao;
import com.example.ikastyle.Dao.HeadGearDao;
import com.example.ikastyle.Dao.MainCategoryDao;
import com.example.ikastyle.Dao.ShoesGearDao;
import com.example.ikastyle.Dao.WeaponMainDao;
import com.example.ikastyle.Dao.WeaponNameDao;
import com.example.ikastyle.DatabaseView.WeaponMain;
import com.example.ikastyle.Entity.ClothingGear;
import com.example.ikastyle.Entity.GearSet;
import com.example.ikastyle.Entity.HeadGear;
import com.example.ikastyle.Entity.MainCategory;
import com.example.ikastyle.Entity.MainName;
import com.example.ikastyle.Entity.ShoesGear;
import com.example.ikastyle.Entity.WeaponName;

@Database(entities = {MainCategory.class, MainName.class, WeaponName.class, GearSet.class, HeadGear.class, ClothingGear.class, ShoesGear.class},
          views = {WeaponMain.class},
          version = 1,
          exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();
    public abstract WeaponNameDao weaponNameDao();
    public abstract WeaponMainDao weaponMainDao();
    public abstract GearSetDao gearSetDao();
    public abstract HeadGearDao headGearDao();
    public abstract ClothingGearDao clothingGearDao();
    public abstract ShoesGearDao shoesGearDao();

    private static AppDatabase instance = null;

    public static AppDatabase getDatabase(Context context){
        if(instance != null)
        {
            return  instance;
        }
        instance = Room.databaseBuilder(context, AppDatabase.class, DatabaseName.DATABASE_NAME)
                .createFromAsset(DatabaseName.DATABASE_FILE_NAME)
                .build();

        return instance;
    }
}
