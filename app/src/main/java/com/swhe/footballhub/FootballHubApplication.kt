package com.swhe.footballhub

import android.app.Application
import timber.log.Timber

class FootballHubApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}