package com.mx3.footballhub

import android.app.Application
import com.mx3.footballhub.data.database.getDatabase
import com.mx3.footballhub.data.repository.CompetitionRepository
import com.mx3.footballhub.data.repository.CompetitionTeamRepository
import com.mx3.footballhub.network.webservice.CompetitionTeamWebService
import com.mx3.footballhub.network.webservice.CompetitionWebService
import com.mx3.footballhub.network.webservice.builder.RetrofitServiceBuilder
import timber.log.Timber

class FootballHubApplication : Application() {

    private val appDatabase by lazy { getDatabase(applicationContext) }

    private val competitionWebService by lazy {
        RetrofitServiceBuilder.buildService(CompetitionWebService::class.java)
    }
    private val competitionTeamWebService by lazy {
        RetrofitServiceBuilder.buildService(CompetitionTeamWebService::class.java)
    }

    val competitionRepository by lazy {
        CompetitionRepository(appDatabase.competitionDao, competitionWebService)
    }
    val competitionTeamRepository by lazy {
        CompetitionTeamRepository(appDatabase.competitionTeamDao, competitionTeamWebService)
    }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}