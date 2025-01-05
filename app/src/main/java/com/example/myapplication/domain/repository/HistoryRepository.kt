package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CardData

interface HistoryRepository {
    suspend fun saveCardData(cardData: CardData):Boolean
    fun getHistory(): List<CardData>
    suspend fun clearHistory()
    suspend fun getInfoByCard(cardNumber: String): CardData?
}