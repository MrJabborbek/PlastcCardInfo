package com.example.myapplication.domain.di

import com.example.myapplication.presentation.screen.main.MainViewModel
import com.example.myapplication.presentation.screen.history.HistoryViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val platformModule: Module
get() = module {
    single { androidApplication() }
//    single { DialPhoneNumber(get()) }
//    single { OpenUrlInBrowser(get()) }

//    singleOf(::DefaultOrdersRepository).bind<OrdersRepository>()

    viewModelOf(::MainViewModel)
    viewModelOf(::HistoryViewModel)



}