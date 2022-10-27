package com.mx3.footballhub.network.model.competition

data class CurrentSeason(
    val id: Int,
    val winner: Winner,
    val currentMatchday: Int,
    val startDate: String?,
    val endDate: String?
)