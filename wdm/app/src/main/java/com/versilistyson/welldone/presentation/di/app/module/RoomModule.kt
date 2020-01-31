package com.versilistyson.welldone.presentation.di.app.module

import android.app.Application
import androidx.room.Room
import com.versilistyson.welldone.data.db.WellDoneDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Singleton
    @JvmStatic
    @Provides
    fun providesWelldoneDatabase(application: Application): WellDoneDatabase =
        Room.databaseBuilder(application, WellDoneDatabase::class.java,
            "well_done_database").fallbackToDestructiveMigration().build()
}