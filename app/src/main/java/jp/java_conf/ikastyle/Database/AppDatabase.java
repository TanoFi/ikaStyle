package jp.java_conf.ikastyle.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import jp.java_conf.ikastyle.Common.Const.DatabaseName;
import jp.java_conf.ikastyle.Dao.ClothingGearDao;
import jp.java_conf.ikastyle.Dao.CustomizationNameDao;
import jp.java_conf.ikastyle.Dao.LoadoutDao;
import jp.java_conf.ikastyle.Dao.HeadGearDao;
import jp.java_conf.ikastyle.Dao.MainCategoryDao;
import jp.java_conf.ikastyle.Dao.ShoesGearDao;
import jp.java_conf.ikastyle.Dao.CustomizationMainDao;
import jp.java_conf.ikastyle.DatabaseView.CustomizationMain;
import jp.java_conf.ikastyle.Entity.ClothingGear;
import jp.java_conf.ikastyle.Entity.CustomizationName;
import jp.java_conf.ikastyle.Entity.Loadout;
import jp.java_conf.ikastyle.Entity.HeadGear;
import jp.java_conf.ikastyle.Entity.MainCategory;
import jp.java_conf.ikastyle.Entity.MainName;
import jp.java_conf.ikastyle.Entity.ShoesGear;

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
