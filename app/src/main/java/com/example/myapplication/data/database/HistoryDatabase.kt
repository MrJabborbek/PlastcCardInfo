package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryEntity::class],
    version = 1,
)
//@ConstructedBy()
abstract class HistoryDatabase: RoomDatabase() {
    abstract val dao: Dao

}