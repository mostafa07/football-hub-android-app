package com.mx3.footballhub.data.model.source.remote.competition

data class CurrentSeason(
    val id: Int,
    val winner: Winner,
    val currentMatchday: Int,
    val startDate: String?,
    val endDate: String?
)