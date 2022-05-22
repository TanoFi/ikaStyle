package com.splatool.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.splatool.ikastyle.Common.Const.DatabaseName;
import com.splatool.ikastyle.Dao.ClothingGearDao;
import com.splatool.ikastyle.Dao.CustomizationNameDao;
import com.splatool.ikastyle.Dao.LoadoutDao;
import com.splatool.ikastyle.Dao.HeadGearDao;
import com.splatool.ikastyle.Dao.MainCategoryDao;
import com.splatool.ikastyle.Dao.ShoesGearDao;
import com.splatool.ikastyle.Dao.CustomizationMainDao;
import com.splatool.ikastyle.DatabaseView.CustomizationMain;
import com.splatool.ikastyle.Entity.ClothingGear;
import com.splatool.ikastyle.Entity.CustomizationName;
import com.splatool.ikastyle.Entity.Loadout;
import com.splatool.ikastyle.Entity.HeadGear;
import com.splatool.ikastyle.Entity.MainCategory;
import com.splatool.ikastyle.Entity.MainName;
import com.splatool.ikastyle.Entity.ShoesGear;

@Database(entities = {MainCategory.class, MainName.class, CustomizationName.class, Loadout.class, HeadGear.class, ClothingGear.class, ShoesGear.class},
          views = {CustomizationMain.class},
          version = 1,
          exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MainCategoryDao mainCategoryDao();
    public abstract CustomizationNameDao customizationNameDao();
    public abstract CustomizationMainDao customizationMainDao();
    public abstract LoadoutDao loadoutDao();
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
