package com.versilistyson.welldone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.versilistyson.welldone.data.db.log.LogDao
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.db.log.LogImageDao
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.db.user.UserDetailsDao
import com.versilistyson.welldone.data.db.user.UserDetailsData

@Database(entities = [SensorData::class, LogData::class, UserDetailsData::class, LogImageData::class], version = 3, exportSchema = false)
abstract class WellDoneDatabase : RoomDatabase() {

    abstract fun sensorDao(): SensorDao
    abstract fun logDao(): LogDao
    abstract fun userDao(): UserDetailsDao
    abstract fun logImageDao(): LogImageDao

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