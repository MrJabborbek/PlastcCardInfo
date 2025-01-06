package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CardData
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveCardData(cardData: CardData):Boolean
    fun getHistory(): Flow<List<CardData>>
    suspend fun clearHistory()
//    suspend fun getInfoByCard(cardNumber: String): CardData?

}