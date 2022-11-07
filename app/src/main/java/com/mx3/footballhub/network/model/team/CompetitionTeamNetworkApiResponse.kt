package com.mx3.footballhub.network.model.team

import com.mx3.footballhub.ui.model.CompetitionTeamEntity

data class CompetitionTeamNetworkApiResponse(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val season: Season,
    val teams: List<Team>
)

data class Team(
    val id: Int,
    val name: String,
    val crest: String,
    val address: String,
    val area: Area,
    val clubColors: String,
    val coach: Coach,
    val founded: Int,
    val lastUpdated: String,
    val marketValue: Any,
    val runningCompetitions: List<RunningCompetition>,
    val shortName: String,
    val squad: List<Any>,
    val staff: List<Any>,
    val tla: String,
    val venue: String,
    val website: String
)

data class Competition(
    val id: Int,
    val code: String,
    val emblem: String,
    val name: String,
    val type: String
)

data class Area(
    val id: Int,
    val code: String,
    val flag: String,
    val name: String
)

data class Coach(
    val id: Any,
    val contract: Contract,
    val dateOfBirth: Any,
    val firstName: Any,
    val lastName: Any,
    val name: Any,
    val nationality: Any
)

data class Contract(
    val start: Any,
    val until: Any
)

data class RunningCompetition(
    val id: Int,
    val code: String,
    val emblem: String,
    val name: String,
    val type: String
)

data class Season(
    val id: Int,
    val currentMatchday: Int,
    val endDate: String,
    val stages: List<String>,
    val startDate: String,
    val winner: Any
)

data class Filters(
    val season: String
)


fun CompetitionTeamNetworkApiResponse.toUiModel(): List<com.mx3.footballhub.data.model.Team> {
    return teams.map {
        com.mx3.footballhub.data.model.Team(
            id = it.id,
            name = it.name,
            crestUrl = it?.crest,
            competitionId = competition.id
        )
    }
}

fun CompetitionTeamNetworkApiResponse.toDatabaseModel(): Array<CompetitionTeamEntity> {
    return teams.map {
        CompetitionTeamEntity(
            id = it.id,
            name = it.name,
            crestUrl = it?.crest,
            competitionId = competition.id
        )
    }.toTypedArray()
}