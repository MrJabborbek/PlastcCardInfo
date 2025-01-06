package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Upsert
    suspend fun upsert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity")
    fun getAll(): Flow<List<HistoryEntity>>

    @Query("DELETE FROM HistoryEntity")
    suspend fun deleteAll()


}