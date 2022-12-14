package com.mx3.footballhub.network.webservice

import com.mx3.footballhub.network.model.competition.AllCompetitionsNetworkApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CompetitionWebService {

    @GET(COMPETITIONS_END_POINT)
    fun getAllCompetitionsAsync(): Deferred<AllCompetitionsNetworkApiResponse>


    companion object {

        private const val COMPETITIONS_END_POINT = "competitions"
    }
}