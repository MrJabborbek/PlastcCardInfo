package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseFactory(
    private val context: Context
) {
    fun create(): RoomDatabase.Builder<HistoryDatabase>{
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath("history.db")

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}