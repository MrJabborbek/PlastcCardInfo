package com.example.myapplication.data.repository

import com.example.myapplication.data.network.RemoteCardDataSource
import com.example.myapplication.domain.model.CardData
import com.example.myapplication.domain.repository.CardInfoRepository
import com.example.myapplication.domain.repository.HistoryRepository

class DefaultCardInfoRepository(
    private val remoteCardDataSource: RemoteCardDataSource
): CardInfoRepository, HistoryRepository {
    override suspend fun saveCardData(cardData: CardData): Boolean {
        TODO("Not yet implemented")
    }

    override fun getHistory(): List<CardData> {
        TODO("Not yet implemented")
    }

    override suspend fun clearHistory() {
        TODO("Not yet implemented")
    }

    override suspend fun getInfoByCard(cardNumber: String): CardData? {
        println("CardData: $cardNumber")
        return remoteCardDataSource.searchCardData(
            query = cardNumber,
        ).also {
            println("CardData: $it")
        }
    }
}