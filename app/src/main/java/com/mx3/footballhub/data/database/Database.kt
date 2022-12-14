package com.mx3.footballhub.data.database

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mx3.footballhub.data.database.dao.CompetitionDao
import com.mx3.footballhub.data.database.dao.CompetitionSeasonDao
import com.mx3.footballhub.data.database.dao.CompetitionTeamDao
import com.mx3.footballhub.ui.model.CompetitionEntity
import com.mx3.footballhub.ui.model.CompetitionSeasonEntity
import com.mx3.footballhub.ui.model.CompetitionTeamEntity

@Database(
    entities = [CompetitionEntity::class, CompetitionTeamEntity::class, CompetitionSeasonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val competitionDao: CompetitionDao
    abstract val competitionTeamDao: CompetitionTeamDao
    abstract val competitionSeasonDao: CompetitionSeasonDao
}

private lateinit var INSTANCE: AppDatabase


fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
    }
    return INSTANCE
}


class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<String?>?): String {
        return Gson().toJson(list)
    }
}