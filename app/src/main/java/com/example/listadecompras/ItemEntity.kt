package com.example.listadecompras.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity<ItemModel>(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String
) {
    fun toModel(onRemove: KFunction1<ItemModel, Unit>) {

    }
}

class KFunction1<T, U> {

}

