package com.hkl.tobuy.dataabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hkl.tobuy.dataabase.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface CategoryEntityDao {
    @Query("SELECT * FROM category_entity")
    fun getAllCategoryEntities(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)

}