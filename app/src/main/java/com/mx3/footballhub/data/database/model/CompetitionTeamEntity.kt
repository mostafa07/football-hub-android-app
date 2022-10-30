package com.mx3.footballhub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mx3.footballhub.data.model.Team

@Entity(tableName = "competition_team")
data class CompetitionTeamEntity(
    @PrimaryKey
    val id: Int,
    val competitionId: Int,
    val name: String,
    val crestUrl: String?
)


fun List<CompetitionTeamEntity>.toDomainModel(): List<Team> {
    return map {
        Team(
            id = it.id,
            competitionId = it.competitionId,
            name = it.name,
            crestUrl = it?.crestUrl
        )
    }
}