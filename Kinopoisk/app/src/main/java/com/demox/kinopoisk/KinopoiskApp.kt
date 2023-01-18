package com.demox.kinopoisk

import android.app.Application
import com.demox.kinopoisk.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class KinopoiskApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@KinopoiskApp)
            modules(koinModules)
        }
    }
}
