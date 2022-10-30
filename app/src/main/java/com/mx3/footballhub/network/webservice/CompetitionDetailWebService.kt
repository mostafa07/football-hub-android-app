package com.mx3.footballhub.network.webservice

import com.mx3.footballhub.network.model.competitiondetail.CompetitionDetailNetworkApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionDetailWebService {

    @GET(COMPETITION_DETAIL_ENDPOINT)
    fun getCompetitionDetailAsync(@Path(ID_PATH_PARAM) id: Int): Deferred<CompetitionDetailNetworkApiResponse>

    companion object {

        const val ID_PATH_PARAM = "id"

        private const val COMPETITION_DETAIL_ENDPOINT = "competitions/{$ID_PATH_PARAM}"
    }
}