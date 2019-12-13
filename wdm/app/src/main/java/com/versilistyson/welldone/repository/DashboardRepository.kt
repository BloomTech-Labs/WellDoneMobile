package com.versilistyson.welldone.repository

import com.versilistyson.welldone.data.remote.DashboardApi
import com.versilistyson.welldone.data.remote.dto.PumpResponse
import com.versilistyson.welldone.util.BASE_URL
import com.versilistyson.welldone.util.StandardClient
import retrofit2.Response

class DashboardRepository {

    val dashboardClient = StandardClient<DashboardApi>(BASE_URL).create(DashboardApi::class.java)

    suspend fun fetchAllPumps(): Response<List<PumpResponse>> {
        return dashboardClient.getPumps()
    }

    suspend fun fetchAllSensors(): Response<List<PumpResponse>> {
        return dashboardClient.getSensors()
    }
}