package com.hkl.tobuy.arch

import com.hkl.tobuy.dataabase.AppDataBase
import com.hkl.tobuy.dataabase.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

class ToBuyRepo(private val appDataBase: AppDataBase) {

    suspend fun addItem(item : ItemEntity) {
        appDataBase.ItemEntityDao().insert(item)
    }
    suspend fun deleteItem(item: ItemEntity) {
        appDataBase.ItemEntityDao().delete(item)
    }
    fun getAllItem() : Flow<List<ItemEntity>> {
        return  appDataBase.ItemEntityDao().getAllItemEntities()
    }

    suspend fun updateItem(item: ItemEntity) {
        appDataBase.ItemEntityDao().update(item)
    }
}