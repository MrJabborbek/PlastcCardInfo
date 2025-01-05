package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CardData

interface CardInfoRepository {
    suspend fun getInfoByCard(cardNumber: String): CardData?
}