package com.mx3.footballhub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mx3.footballhub.data.model.Competition

@Entity(tableName = "competition")
data class CompetitionEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val code: String?,
    val emblemUrl: String?,
    val currentSeasonStartDate: String?,
    val currentSeasonEndDate: String?
)


fun List<CompetitionEntity>.toDomainModel(): List<Competition> {
    return map {
        Competition(
            id = it.id,
            name = it.name,
            code = it.name,
            emblemUrl = it.emblemUrl,
            currentSeasonStartDate = it.currentSeasonStartDate,
            currentSeasonEndDate = it.currentSeasonEndDate
        )
    }
}