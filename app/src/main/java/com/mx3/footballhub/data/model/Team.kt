package com.mx3.footballhub.data.model

data class Team(
    val id: Int,
    val name: String,
    val crestUrl: String?,
    val competitionId: Int
)
