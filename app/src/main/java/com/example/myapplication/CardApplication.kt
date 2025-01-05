package com.example.myapplication

import android.app.Application
import com.example.myapplication.domain.di.initKoin
import org.koin.android.ext.koin.androidContext

class CardApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CardApplication)
        }
    }
}