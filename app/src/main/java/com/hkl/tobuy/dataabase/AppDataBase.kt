package com.hkl.tobuy.dataabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hkl.tobuy.dataabase.dao.ItemEntityDao
import com.hkl.tobuy.dataabase.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
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
                ).build()
                return  appDataBase!!
            }
        }
    }
    abstract fun ItemEntityDao() : ItemEntityDao
}