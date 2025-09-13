package com.hkl.tobuy.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hkl.tobuy.dataabase.AppDataBase
import com.hkl.tobuy.dataabase.entity.ItemEntity

class ToBuyViewModel : ViewModel() {
    private lateinit var repo: ToBuyRepo
    val itemListLiveData =  MutableLiveData<List<ItemEntity>>()

    fun init(appDataBase: AppDataBase){
        repo = ToBuyRepo(appDataBase)
        val items  = repo.getAllItem()
        itemListLiveData.postValue(items)
    }

    fun addItem(item : ItemEntity){
        repo.addItem(item)
    }
    fun deleteItem(item : ItemEntity) {
        repo.deleteItem(item)
    }
}