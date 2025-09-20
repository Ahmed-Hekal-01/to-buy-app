package com.hkl.tobuy.dataabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hkl.tobuy.dataabase.dao.CategoryEntityDao
import com.hkl.tobuy.dataabase.dao.ItemEntityDao
import com.hkl.tobuy.dataabase.entity.CategoryEntity
import com.hkl.tobuy.dataabase.entity.ItemEntity

@Database(
    entities = [ItemEntity::class, CategoryEntity::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var appDataBase: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
            if (appDataBase != null) {
                return appDataBase!!
            } else {
                appDataBase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "to-buy-database"
                )
                    .addMigrations(Migration_1_2())
                    .build()
                return appDataBase!!
            }
        }
    }

    class Migration_1_2 : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS `category_entity` (`id` TEXT NOT NULL , `name` TEXT NOT NULL , PRIMARY KEY (`id`))"
            )
        }
    }

    abstract fun ItemEntityDao(): ItemEntityDao
    abstract fun CategoryEntityDao(): CategoryEntityDao
}