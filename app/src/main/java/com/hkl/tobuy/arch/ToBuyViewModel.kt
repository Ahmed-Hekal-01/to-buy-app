package com.hkl.tobuy.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkl.tobuy.dataabase.AppDataBase
import com.hkl.tobuy.dataabase.entity.ItemEntity
import kotlinx.coroutines.launch

class ToBuyViewModel : ViewModel() {
    private lateinit var repo: ToBuyRepo
    val itemListLiveData =  MutableLiveData<List<ItemEntity>>()

    val transactionLiveData  = MutableLiveData<Boolean>()
    fun init(appDataBase: AppDataBase){
        repo = ToBuyRepo(appDataBase)
        viewModelScope.launch {
            repo.getAllItem().collect { items ->
                itemListLiveData.postValue(items)
            }
        }
    }

    fun addItem(item : ItemEntity){
        viewModelScope.launch {
            repo.addItem(item)
        }
        transactionLiveData.postValue(true)
    }

    fun deleteItem(item : ItemEntity) {
        viewModelScope.launch {
            repo.deleteItem(item)
        }
    }

    fun updateItem(item : ItemEntity) {
        viewModelScope.launch {
            repo.updateItem(item)

            transactionLiveData.postValue(true)
        }
    }
}