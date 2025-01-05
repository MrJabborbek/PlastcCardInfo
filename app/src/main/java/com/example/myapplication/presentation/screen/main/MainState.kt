package com.example.myapplication.presentation.screen.main

import com.example.myapplication.domain.model.CardData

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cardData: CardData? = null,
    val enteredCardNumber: String = ""
)
