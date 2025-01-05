package com.example.myapplication.data.network


import com.example.myapplication.domain.model.CardData
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://lookup.binlist.net/"

class KtorRemoteCardDataSource(
    private val httpClient: HttpClient
): RemoteCardDataSource {

    override suspend fun searchCardData(
        query: String,
        resultLimit: Int?
    ): CardData? {
        return safeCall<CardData> {
            httpClient.get(
                urlString = "$BASE_URL/$query"
            )
        }
    }
//
//    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
//        return safeCall<BookWorkDto> {
//            httpClient.get(
//                urlString = "$BASE_URL/works/$bookWorkId.json"
//            )
//        }
//    }
}