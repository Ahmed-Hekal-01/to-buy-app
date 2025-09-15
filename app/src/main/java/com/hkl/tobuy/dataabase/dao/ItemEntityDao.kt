package com.hkl.tobuy.dataabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hkl.tobuy.dataabase.entity.ItemEntity
@Dao
interface ItemEntityDao {
    @Query("SELECT * FROM item_entity")
    suspend fun getAllItemEntities(): List<ItemEntity>

    @Insert
    suspend fun insert(itemEntity: ItemEntity)

    @Delete
    suspend fun delete(itemEntity: ItemEntity)
}
