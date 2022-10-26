package com.mx3.footballhub

import android.app.Application
import com.mx3.footballhub.data.database.getDatabase
import com.mx3.footballhub.data.repository.CompetitionRepository
import timber.log.Timber

class FootballHubApplication : Application() {

    private val appDatabase by lazy { getDatabase(applicationContext) }
    val competitionRepository by lazy { CompetitionRepository(appDatabase.competitionDao) }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}