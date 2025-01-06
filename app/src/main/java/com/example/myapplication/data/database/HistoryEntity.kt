package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey
    val cardNumber: String = "",
    val length: Int? = null,
    val luhn: Boolean? = null,
    val scheme: String? = null,
    val city: String? = null,
    val countryName: String? = null,
    val phone: String? = null,
    val url: String? = null,
    val brand: String? = null,
    val alpha2: String? =null,
    val currency: String? =null,
    val emoji: String? =null,
    val latitude: Int? =null,
    val longitude: Int? =null,
    val bankName: String? =null,
    val numeric: String? =null,
    val prepaid: Boolean? = null,
    val type: String? = null
)