package com.mx3.footballhub.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx3.footballhub.data.database.model.CompetitionSeasonEntity

@Dao
interface CompetitionSeasonDao {

    @Query("SELECT * FROM competition_season")
    fun getAllCompetitionSeasons(): LiveData<List<CompetitionSeasonEntity>>

    @Query("SELECT * FROM competition_season WHERE competitionId = :competitionId")
    fun getCompetitionSeasonsByCompetitionId(competitionId: Int): LiveData<List<CompetitionSeasonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg competitionSeasons: CompetitionSeasonEntity)
}