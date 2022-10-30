package com.mx3.footballhub.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx3.footballhub.data.database.model.CompetitionTeamEntity

@Dao
interface CompetitionTeamDao {

    @Query("SELECT * FROM competition_team")
    fun getAllCompetitionTeams(): LiveData<List<CompetitionTeamEntity>>

    @Query("SELECT * FROM competition_team WHERE competitionId = :competitionId")
    fun getCompetitionTeamsByCompetitionId(competitionId: Int): LiveData<List<CompetitionTeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg competitionTeams: CompetitionTeamEntity)
}