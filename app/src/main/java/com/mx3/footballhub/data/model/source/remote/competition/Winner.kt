package com.mx3.footballhub.data.model.source.remote.competition

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