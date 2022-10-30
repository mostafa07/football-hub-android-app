package com.mx3.footballhub.network.model.competitiondetail

import com.mx3.footballhub.data.database.model.CompetitionSeasonEntity

data class CompetitionDetailNetworkApiResponse(
    val id: Int,
    val name: String,
    val seasons: List<Season>,
    val area: Area,
    val code: String,
    val currentSeason: CurrentSeason,
    val emblem: String,
    val lastUpdated: String,
    val type: String
)

data class Season(
    val id: Int,
    val currentMatchday: Int,
    val startDate: String,
    val endDate: String,
    val stages: List<String>,
    val winner: Winner
)

data class Area(
    val id: Int,
    val name: String,
    val code: String,
    val flag: String
)

data class CurrentSeason(
    val id: Int,
    val currentMatchday: Int,
    val startDate: String,
    val endDate: String,
    val stages: List<String>,
    val winner: Any
)

data class Winner(
    val id: Int,
    val name: String,
    val address: String,
    val clubColors: String,
    val crest: String,
    val founded: Int,
    val lastUpdated: String,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
)


fun CompetitionDetailNetworkApiResponse.toSeasonDomainModel(): List<com.mx3.footballhub.data.model.Season> {
    return seasons.map {
        com.mx3.footballhub.data.model.Season(
            id = it.id,
            competitionId = id,
            startDate = it?.startDate,
            endDate = it?.endDate,
            winnerName = it?.winner?.name
        )
    }
}

fun CompetitionDetailNetworkApiResponse.toSeasonDatabaseModel(): Array<CompetitionSeasonEntity> {
    return seasons.map {
        CompetitionSeasonEntity(
            id = it.id,
            competitionId = id,
            startDate = it?.startDate,
            endDate = it?.endDate,
            winnerName = it?.winner?.name
        )
    }.toTypedArray()
}