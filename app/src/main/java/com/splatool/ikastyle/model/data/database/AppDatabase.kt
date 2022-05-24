package com.splatool.ikastyle.model.data.database

import android.content.Context
import com.splatool.ikastyle.model.data.dao.HeadGearDao
import com.splatool.ikastyle.model.data.dao.ClothingGearDao
import com.splatool.ikastyle.model.data.dao.ShoesGearDao
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.ShoesGear
import com.splatool.ikastyle.model.data.dao.LoadoutDao
import com.splatool.ikastyle.model.data.entity.MainCategory
import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import com.splatool.ikastyle.model.data.entity.CustomizationName
import androidx.room.Database
import com.splatool.ikastyle.model.data.entity.MainName
import androidx.room.RoomDatabase
import com.splatool.ikastyle.model.data.dao.MainCategoryDao
import com.splatool.ikastyle.model.data.dao.CustomizationNameDao
import com.splatool.ikastyle.model.data.dao.CustomizationMainDao
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