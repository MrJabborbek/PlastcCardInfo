package com.example.myapplication.data.network

import com.example.myapplication.domain.model.CardData

interface RemoteCardDataSource {
    suspend fun searchCardData(
        query: String,
        resultLimit: Int? = null,
    ): CardData?
}