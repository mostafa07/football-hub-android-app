package com.mx3.footballhub.network.model.competition

import com.mx3.footballhub.ui.model.CompetitionEntity

data class AllCompetitionsNetworkApiResponse(
    val competitions: List<Competition>,
    val count: Int,
    val filters: Filters
)

data class Competition(
    val area: Area,
    val code: String?,
    val currentSeason: CurrentSeason,
    val emblem: String?,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val numberOfAvailableSeasons: Int,
    val plan: String,
    val type: String
)

data class Area(
    val code: String,
    val flag: String,
    val id: Int,
    val name: String
)

data class CurrentSeason(
    val id: Int,
    val winner: Winner,
    val currentMatchday: Int,
    val startDate: String?,
    val endDate: String?
)

data class Winner(
    val id: Int,
    val name: String,
    val shortName: String,
    val address: String,
    val clubColors: String,
    val crest: String,
    val founded: Int,
    val lastUpdated: String,
    val tla: String,
    val venue: String,
    val website: String
)

class Filters


fun AllCompetitionsNetworkApiResponse.toUiModel(): List<com.mx3.footballhub.data.model.Competition> {
    return competitions.map {
        com.mx3.footballhub.data.model.Competition(
            id = it.id,
            name = it.name,
            code = it?.code,
            emblemUrl = it?.emblem,
            currentSeasonStartDate = it?.currentSeason?.startDate,
            currentSeasonEndDate = it?.currentSeason?.endDate
        )
    }
}

fun AllCompetitionsNetworkApiResponse.toDatabaseModel(): Array<CompetitionEntity> {
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