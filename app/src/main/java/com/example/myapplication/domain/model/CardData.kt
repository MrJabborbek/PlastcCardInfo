package com.example.myapplication.domain.model

import kotlinx.serialization.Serializable

//@Serializable
//data class CardData(
//    val cardNumber: String = "",
//    val bank: Bank,
//    val country: Country,
//    val number: Number,
//    val scheme: String,
//    val type: String
//)

@Serializable
data class CardData(
    val cardNumber: String = "",
    val number: Number? = null,
    val scheme: String? = null,
    val bank: Bank? = null,
    val brand: String? = null,
    val country: Country? = null,
    val prepaid: Boolean? = null,
    val type: String? = null
)