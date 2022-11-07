package com.mx3.footballhub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mx3.footballhub.data.model.Season

@Entity(tableName = "competition_season")
data class CompetitionSeasonEntity(
    @PrimaryKey
    val id: Int,
    val competitionId: Int,
    val startDate: String?,
    val endDate: String?,
    val winnerName: String?
)


fun List<CompetitionSeasonEntity>.toUiModel(): List<Season> {
    return map {
        Season(
            id = it.id,
            competitionId = it.competitionId,
            startDate = it?.startDate,
            endDate = it?.endDate,
            winnerName = it?.winnerName
        )
    }
}
