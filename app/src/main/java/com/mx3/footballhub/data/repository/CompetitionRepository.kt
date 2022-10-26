package com.mx3.footballhub.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mx3.footballhub.data.database.getDatabase
import com.mx3.footballhub.data.model.domain.Competition
import com.mx3.footballhub.data.model.source.local.toDomainModel
import com.mx3.footballhub.data.model.source.remote.competition.toDatabaseModel
import com.mx3.footballhub.webservice.CompetitionWebService
import com.mx3.footballhub.webservice.builder.RetrofitServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetitionRepository(context: Context) {

    private val database = getDatabase(context.applicationContext)
    private val competitionWebService =
        RetrofitServiceBuilder.buildService(CompetitionWebService::class.java)

    val competitions: LiveData<List<Competition>> =
        Transformations.map(database.competitionDao.getAllCompetitions()) {
            it.toDomainModel()
        }


    suspend fun getAllCompetitions() {
        withContext(Dispatchers.IO) {
            val response = competitionWebService.getAllCompetitionsAsync().await()
            database.competitionDao.insertAll(*response.toDatabaseModel())
        }
    }
}