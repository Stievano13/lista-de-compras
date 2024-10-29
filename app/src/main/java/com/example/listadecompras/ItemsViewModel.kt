

package com.example.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.data.ItemsDatabase
import com.example.listadecompras.data.toModel
import com.example.listadecompras.data.ItemEntity
import com.example.listadecompras.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(private val database: ItemsDatabase) : ViewModel() {

    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    private suspend fun fetchAll() {
        val result = database.itemsDao().getAll().map { it.toModel(onRemove = ::removeItem) }
        itemsLiveData.postValue(result)
    }

    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity<Any>(id = 0, name = name)
            database.itemsDao().insert(entity)
            fetchAll()
        }
    }

    private fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }

    fun getItemsLiveData() = itemsLiveData
}
