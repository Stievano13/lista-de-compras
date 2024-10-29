package com.example.listadecompras.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listadecompras.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}
