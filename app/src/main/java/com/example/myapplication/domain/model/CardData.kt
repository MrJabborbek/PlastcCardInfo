package com.example.myapplication.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CardData(
    val cardNumber: String = "",
    val bank: Bank,
    val country: Country,
    val number: Number,
    val scheme: String,
    val type: String
)

//@Serializable
//data class CardData(
//    val number: Number,
//    val scheme: String,
//    val cardNumber: String = "",
//    val bank: Bank,
//    val brand: String,
//    val country: Country,
//    val prepaid: Boolean,
//    val type: String
//)