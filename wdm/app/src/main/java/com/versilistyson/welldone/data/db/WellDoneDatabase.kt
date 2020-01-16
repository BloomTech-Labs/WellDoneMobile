package com.versilistyson.welldone.data.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.db.user.UserDao
import com.versilistyson.welldone.data.db.user.UserData

@Database(entities = [SensorData::class, UserData::class], version = 1, exportSchema = false)
abstract class WellDoneDatabase : RoomDatabase() {

    abstract fun sensorDao(): SensorDao
    abstract fun userDao(): UserDao

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
        /*fun getInstance(context: Context): WellDoneDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = databaseBuilder(
                        context.applicationContext,
                        WellDoneDatabase::class.java,
                        "well_done_database"
                    )
                }
            }

        }*/
    }
}