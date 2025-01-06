package com.example.myapplication.domain.model

import kotlinx.serialization.Serializable

//@Serializable
//data class Country(
//    val alpha2: String,
//    val currency: String,
//    val emoji: String,
//    val latitude: Int,
//    val longitude: Int,
//    val name: String,
//    val numeric: String
//)
@Serializable
data class Country(
    val alpha2: String? =null,
    val currency: String? =null,
    val emoji: String? =null,
    val latitude: Int? =null,
    val longitude: Int? =null,
    val name: String? =null,
    val numeric: String? =null
)