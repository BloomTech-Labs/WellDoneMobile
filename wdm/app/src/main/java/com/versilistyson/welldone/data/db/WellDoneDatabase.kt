package com.versilistyson.welldone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData

@Database(entities = [SensorData::class], version = 1, exportSchema = false)
abstract class WellDoneDatabase : RoomDatabase() {

    abstract fun sensorDao(): SensorDao
    abstract fun logDao(): LogDao

    companion object {
        @Volatile
        private var INSTANCE: WellDoneDatabase? = null

        fun getInstance(context: Context): WellDoneDatabase {
            val database by lazy {
                Room.databaseBuilder(
                    context,
                    WellDoneDatabase::class.java,
                    "well_done_database"
                ).fallbackToDestructiveMigration().build()
            }
            return database
        }
    }
}