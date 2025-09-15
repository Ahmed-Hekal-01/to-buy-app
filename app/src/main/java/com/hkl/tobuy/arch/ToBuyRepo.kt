package com.hkl.tobuy.arch

import com.hkl.tobuy.dataabase.AppDataBase
import com.hkl.tobuy.dataabase.entity.ItemEntity

class ToBuyRepo(private val appDataBase: AppDataBase) {

    suspend fun addItem(item : ItemEntity) {
        appDataBase.ItemEntityDao().insert(item)
    }
    suspend fun deleteItem(item: ItemEntity) {
        appDataBase.ItemEntityDao().delete(item)
    }
    suspend fun getAllItem() : List<ItemEntity>{
        return  appDataBase.ItemEntityDao().getAllItemEntities()
    }
}