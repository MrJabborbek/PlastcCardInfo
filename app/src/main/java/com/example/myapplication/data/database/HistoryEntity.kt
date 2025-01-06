package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey
    val cardNumber: String = "",
    val bankName: String,
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val countryName: String,
    val numeric: String,
    val scheme: String,
    val type: String
)