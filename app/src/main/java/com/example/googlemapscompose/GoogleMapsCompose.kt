package com.example.googlemapscompose

import android.app.Application
import com.example.googlemapscompose.di.module.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GoogleMapsCompose:Application() {
    override fun onCreate() {
        super.onCreate()
        config()
    }

    private fun config() {
        startKoin {
            androidContext(this@GoogleMapsCompose)
            androidLogger(Level.DEBUG)
            modules(Test.modules())
        }
    }
}