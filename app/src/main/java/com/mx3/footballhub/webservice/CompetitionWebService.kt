package com.mx3.footballhub.webservice

import com.mx3.footballhub.data.model.source.remote.competition.CompetitionNetworkApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CompetitionWebService {

    @GET(GET_ALL_COMPETITIONS_END_POINT)
    fun getAllCompetitionsAsync(): Deferred<CompetitionNetworkApiResponse>


    companion object {
        const val GET_ALL_COMPETITIONS_END_POINT = "competitions"
    }
}