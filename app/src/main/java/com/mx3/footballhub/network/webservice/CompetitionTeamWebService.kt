package com.mx3.footballhub.network.webservice

import com.mx3.footballhub.network.model.team.CompetitionTeamNetworkApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionTeamWebService {

    @GET(COMPETITION_TEAMS_ENDPOINT)
    fun getCompetitionTeamsByCompetitionIdAsync(@Path(COMPETITION_ID_PATH_PARAM) competitionId: Int)
            : Deferred<CompetitionTeamNetworkApiResponse>


    companion object {

        const val COMPETITION_ID_PATH_PARAM = "competitionId"

        private const val COMPETITION_TEAMS_ENDPOINT = "competitions/{$COMPETITION_ID_PATH_PARAM}/teams"
    }
}