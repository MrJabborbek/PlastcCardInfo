package com.example.myapplication.presentation.screen.history

import com.example.myapplication.domain.model.CardData

data class HistoryState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val history: List<CardData> = emptyList()
)