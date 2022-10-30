package com.mx3.footballhub.data.model

data class Season(
    val id: Int,
    val competitionId: Int,
    val startDate: String?,
    val endDate: String?,
    val winnerName: String?
)
