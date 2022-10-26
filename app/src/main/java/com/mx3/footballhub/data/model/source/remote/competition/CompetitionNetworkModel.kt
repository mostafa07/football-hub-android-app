package com.mx3.footballhub.data.model.source.remote.competition

data class CompetitionNetworkModel(
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