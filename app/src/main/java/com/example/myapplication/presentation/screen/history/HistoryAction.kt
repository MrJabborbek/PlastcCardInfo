package com.example.myapplication.presentation.screen.history

import com.example.myapplication.domain.model.CardData

sealed interface HistoryAction {
    data class OnHistoryItemClicked(val cardData: CardData) : HistoryAction
    data object OnClearHistoryClicked : HistoryAction
    data object OnBackClicked: HistoryAction
}