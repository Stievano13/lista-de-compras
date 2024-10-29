package com.example.listadecompras.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {
    @Insert
    suspend fun insert(item: ItemEntity<Any?>): Long

    @Delete
    suspend fun delete(item: ItemEntity<Any?>): Int

    @Query("SELECT * FROM items")
    suspend fun getAll(): List<ItemEntity<Any?>>
}








