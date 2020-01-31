package com.versilistyson.welldone.presentation.di.app.module

import android.app.Application
import androidx.room.Room
import com.versilistyson.welldone.data.db.WellDoneDatabase
import com.versilistyson.welldone.presentation.di.dashboard.DashboardActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private val wellDoneDatabase = Room.databaseBuilder(
        application, WellDoneDatabase::class.java, "well_done_database").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesWelldoneDatabase(): WellDoneDatabase{
        return wellDoneDatabase
    }
}