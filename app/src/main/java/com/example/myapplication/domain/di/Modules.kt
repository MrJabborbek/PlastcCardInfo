package com.example.myapplication.domain.di

import androidx.compose.material3.SnackbarHostState
import com.example.myapplication.data.network.HttpClientFactory
import com.example.myapplication.data.network.KtorRemoteCardDataSource
import com.example.myapplication.data.network.RemoteCardDataSource
import com.example.myapplication.data.repository.DefaultCardInfoRepository
import com.example.myapplication.domain.repository.CardInfoRepository
import com.example.myapplication.domain.repository.HistoryRepository
import com.example.myapplication.presentation.screen.main.MainViewModel
import com.example.myapplication.presentation.screen.history.HistoryViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module


val platformModule: Module
get() = module {
    single { androidApplication() }
    single<HttpClientEngine> { OkHttp.create() }
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteCardDataSource).bind<RemoteCardDataSource>()
    singleOf(::DefaultCardInfoRepository).binds(arrayOf( CardInfoRepository::class, HistoryRepository::class))
//    single { DialPhoneNumber(get()) }
//    single { OpenUrlInBrowser(get()) }

//    singleOf(::DefaultOrdersRepository).bind<OrdersRepository>()
    single { SnackbarHostState() }
    viewModelOf(::MainViewModel)
    viewModelOf(::HistoryViewModel)



}