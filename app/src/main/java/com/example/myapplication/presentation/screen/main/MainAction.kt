package com.example.myapplication.presentation.screen.main

sealed interface MainAction {
    data class OnCardNumberEntered(val value: String): MainAction
    data object OnHistoryClicked: MainAction
    data object OnSearchClicked: MainAction
}