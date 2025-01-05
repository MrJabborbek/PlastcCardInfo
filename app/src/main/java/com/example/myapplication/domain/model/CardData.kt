package com.example.myapplication.domain.model

data class CardData(
    val cardNumber: String = "",
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)