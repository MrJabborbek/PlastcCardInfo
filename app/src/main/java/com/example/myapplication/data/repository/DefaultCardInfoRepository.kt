package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Dao
import com.example.myapplication.data.database.HistoryEntity
import com.example.myapplication.data.network.RemoteCardDataSource
import com.example.myapplication.domain.model.Bank
import com.example.myapplication.domain.model.CardData
import com.example.myapplication.domain.model.Country
import com.example.myapplication.domain.model.Number
import com.example.myapplication.domain.repository.CardInfoRepository
import com.example.myapplication.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultCardInfoRepository(
    private val remoteCardDataSource: RemoteCardDataSource,
    private val dao: Dao
): CardInfoRepository, HistoryRepository {
    override suspend fun saveCardData(cardData: CardData): Boolean {
        return try {
            dao.upsert(
                HistoryEntity(
                    cardNumber = cardData.cardNumber,
                    length = cardData.number?.length,
                    luhn = cardData.number?.luhn,
                    scheme = cardData.scheme,
                    city = cardData.bank?.city,
                    countryName = cardData.country?.name,
                    phone = cardData.bank?.phone,
                    url = cardData.bank?.url,
                    brand = cardData.brand,
                    alpha2 = cardData.country?.alpha2,
                    currency = cardData.country?.currency,
                    emoji = cardData.country?.emoji,
                    latitude = cardData.country?.latitude,
                    longitude = cardData.country?.longitude,
                    bankName = cardData.bank?.name,
                    numeric = cardData.country?.numeric,
                    prepaid = cardData.prepaid,
                    type = cardData.type
                )
            )
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    override fun getHistory(): Flow<List<CardData>> {
        return dao
            .getAll()
            .map { historyEntities ->
                historyEntities.map { historyEntity ->
                    CardData(
                        cardNumber = historyEntity.cardNumber,
                        number = Number(
                            length = historyEntity.length,
                            luhn = historyEntity.luhn
                        ),
                        scheme = historyEntity.scheme,
                        bank = Bank(
                            city = historyEntity.city,
                            name = historyEntity.bankName,
                            phone = historyEntity.phone,
                            url = historyEntity.url
                        ),
                        brand = historyEntity.brand,
                        country = Country(
                            alpha2 = historyEntity.alpha2,
                            currency = historyEntity.currency,
                            emoji = historyEntity.emoji,
                            latitude = historyEntity.latitude,
                            longitude = historyEntity.longitude,
                            name = historyEntity.countryName,
                            numeric = historyEntity.numeric
                        ),
                        prepaid = historyEntity.prepaid,
                        type = historyEntity.type
                    )
                }
        }
    }

    override suspend fun clearHistory() {
        dao.deleteAll()
    }

    override suspend fun getInfoByCard(cardNumber: String): CardData? {
        if (cardNumber.length < 6) return null
//        return CardData(
//            cardNumber = cardNumber,
//            bank= Bank(name="Aloqabank"),
//            country= Country(alpha2="UZ", currency="UZS", emoji="🇺🇿", latitude=41, longitude=64, name="Uzbekistan", numeric="860"),
//            number=Number(),
//            scheme="humocard",
//            type="debit"
//        ).also {
//            saveCardData(it)
//        }
        return remoteCardDataSource.searchCardData(
            query = cardNumber,
        )?.copy(cardNumber = cardNumber).also { // check if it is working
            it?.let { saveCardData(it) }
        }
    }
}