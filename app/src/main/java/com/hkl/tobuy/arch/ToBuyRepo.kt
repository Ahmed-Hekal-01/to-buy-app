package com.hkl.tobuy.arch

import com.hkl.tobuy.dataabase.AppDataBase
import com.hkl.tobuy.dataabase.entity.ItemEntity

class ToBuyRepo(private val appDataBase: AppDataBase) {

    fun addItem(item : ItemEntity) {
        appDataBase.ItemEntityDao().insert(item)
    }
    fun deleteItem(item: ItemEntity) {
        appDataBase.ItemEntityDao().delete(item)
    }
    fun getAllItem() : List<ItemEntity>{
        return  appDataBase.ItemEntityDao().getAllItemEntities()
    }
}