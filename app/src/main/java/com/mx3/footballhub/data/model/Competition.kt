package com.mx3.footballhub.data.model

data class Competition(
    val id: Int,
    val name: String,
    val code: String?,
    val emblemUrl: String?,
    val currentSeasonStartDate: String?,
    val currentSeasonEndDate: String?
)
