package com.mx3.footballhub.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx3.footballhub.data.database.model.CompetitionEntity

@Dao
interface CompetitionDao {

    @Query("SELECT * FROM competition")
    fun getAllCompetitions(): LiveData<List<CompetitionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg competitions: CompetitionEntity)
}