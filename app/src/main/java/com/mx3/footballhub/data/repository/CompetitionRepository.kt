package com.mx3.footballhub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mx3.footballhub.data.database.dao.CompetitionDao
import com.mx3.footballhub.data.database.model.toDomainModel
import com.mx3.footballhub.data.model.Competition
import com.mx3.footballhub.network.model.competition.toDatabaseModel
import com.mx3.footballhub.network.webservice.CompetitionWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetitionRepository(
    private val competitionDao: CompetitionDao,
    private val competitionWebService: CompetitionWebService
) {

    val competitions: LiveData<List<Competition>> =
        Transformations.map(competitionDao.getAllCompetitions()) { it.toDomainModel() }


    suspend fun getAllCompetitions() {
        withContext(Dispatchers.IO) {
            val response = competitionWebService.getAllCompetitionsAsync().await()
            competitionDao.insertAll(*response.toDatabaseModel())
        }
    }
}