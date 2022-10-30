package com.mx3.footballhub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mx3.footballhub.data.database.dao.CompetitionTeamDao
import com.mx3.footballhub.data.database.model.toDomainModel
import com.mx3.footballhub.data.model.Team
import com.mx3.footballhub.network.model.team.toDatabaseModel
import com.mx3.footballhub.network.webservice.CompetitionTeamWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetitionTeamRepository(
    private val competitionTeamDao: CompetitionTeamDao,
    private val competitionTeamWebService: CompetitionTeamWebService
) {

    val competitionTeams: LiveData<List<Team>> by lazy {
        Transformations.map(competitionTeamDao.getAllCompetitionTeams()) {
            it.toDomainModel()
        }
    }

    suspend fun getCompetitionTeamsByCompetitionId(competitionId: Int) {
        withContext(Dispatchers.IO) {
            val response = competitionTeamWebService
                .getCompetitionTeamsByCompetitionIdAsync(competitionId).await()
            competitionTeamDao.insertAll(*response.toDatabaseModel())
        }
    }
}