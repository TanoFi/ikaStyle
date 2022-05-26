package com.splatool.ikastyle.database

import android.content.Context
import com.splatool.ikastyle.dao.HeadGearDao
import com.splatool.ikastyle.dao.ClothingGearDao
import com.splatool.ikastyle.dao.ShoesGearDao
import com.splatool.ikastyle.entity.Loadout
import com.splatool.ikastyle.entity.HeadGear
import com.splatool.ikastyle.entity.ClothingGear
import com.splatool.ikastyle.entity.ShoesGear
import com.splatool.ikastyle.dao.LoadoutDao
import com.splatool.ikastyle.entity.MainCategory
import com.splatool.ikastyle.databaseView.CustomizationMain
import com.splatool.ikastyle.entity.CustomizationName
import androidx.room.Database
import com.splatool.ikastyle.entity.MainName
import androidx.room.RoomDatabase
import com.splatool.ikastyle.dao.MainCategoryDao
import com.splatool.ikastyle.dao.CustomizationNameDao
import com.splatool.ikastyle.dao.CustomizationMainDao
import androidx.room.Room
import com.splatool.ikastyle.common.const.DatabaseName

@Database(
    entities = [MainCategory::class, MainName::class, CustomizationName::class, Loadout::class, HeadGear::class, ClothingGear::class, ShoesGear::class],
    views = [CustomizationMain::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainCategoryDao(): MainCategoryDao
    abstract fun customizationNameDao(): CustomizationNameDao
    abstract fun customizationMainDao(): CustomizationMainDao
    abstract fun loadoutDao(): LoadoutDao
    abstract fun headGearDao(): HeadGearDao
    abstract fun clothingGearDao(): ClothingGearDao
    abstract fun shoesGearDao(): ShoesGearDao

    companion object {
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (instance != null) {
                return instance!!
            }
            instance =
                Room.databaseBuilder(context, AppDatabase::class.java, DatabaseName.DATABASE_NAME)
                    .createFromAsset(DatabaseName.DATABASE_FILE_NAME)
                    .build()
            return instance!!
        }
    }
}