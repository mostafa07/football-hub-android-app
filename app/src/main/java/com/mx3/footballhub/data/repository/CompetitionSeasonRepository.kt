package com.mx3.footballhub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mx3.footballhub.data.database.dao.CompetitionSeasonDao
import com.mx3.footballhub.data.database.model.toUiModel
import com.mx3.footballhub.data.model.Season
import com.mx3.footballhub.network.model.competitiondetail.toSeasonDatabaseModel
import com.mx3.footballhub.network.webservice.CompetitionDetailWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetitionSeasonRepository(
    private val competitionSeasonDao: CompetitionSeasonDao,
    private val competitionDetailWebService: CompetitionDetailWebService
) {

    val competitionSeasons: LiveData<List<Season>> by lazy {
        Transformations.map(competitionSeasonDao.getAllCompetitionSeasons()) {
            it.toUiModel()
        }
    }

    suspend fun getCompetitionSeasonsByCompetitionId(competitionId: Int) {
        withContext(Dispatchers.IO) {
            val response = competitionDetailWebService
                .getCompetitionDetailAsync(competitionId).await()
            competitionSeasonDao.insertAll(*response.toSeasonDatabaseModel())
        }
    }
}