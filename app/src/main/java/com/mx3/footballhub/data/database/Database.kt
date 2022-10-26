package com.mx3.footballhub.data.database

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mx3.footballhub.data.database.dao.CompetitionDao
import com.mx3.footballhub.data.model.source.local.CompetitionEntity

@Database(entities = [CompetitionEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val competitionDao: CompetitionDao
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