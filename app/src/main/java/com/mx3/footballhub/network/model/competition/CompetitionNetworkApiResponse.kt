package com.mx3.footballhub.network.model.competition

import com.mx3.footballhub.data.database.model.CompetitionEntity
import com.mx3.footballhub.data.model.Competition

data class CompetitionNetworkApiResponse(
    val competitions: List<CompetitionNetworkModel>,
    val count: Int,
    val filters: Filters
)


fun CompetitionNetworkApiResponse.toDomainModel(): List<Competition> {
    return competitions.map {
        Competition(
            id = it.id,
            name = it.name,
            code = it?.code,
            emblemUrl = it?.emblem,
            currentSeasonStartDate = it?.currentSeason?.startDate,
            currentSeasonEndDate = it?.currentSeason?.endDate
        )
    }
}

fun CompetitionNetworkApiResponse.toDatabaseModel(): Array<CompetitionEntity> {
    return competitions.map {
        CompetitionEntity(
            id = it.id,
            name = it.name,
            code = it?.code,
            emblemUrl = it?.emblem,
            currentSeasonStartDate = it?.currentSeason?.startDate,
            currentSeasonEndDate = it?.currentSeason?.endDate
        )
    }.toTypedArray()
}