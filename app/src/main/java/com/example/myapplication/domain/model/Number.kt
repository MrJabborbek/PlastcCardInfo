package com.example.myapplication.domain.model

import kotlinx.serialization.Serializable

//@Serializable
//class Number
@Serializable
data class Number(
    val length: Int? = null,
    val luhn: Boolean? = null
)