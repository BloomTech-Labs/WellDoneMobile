package com.versilistyson.welldone.repository

import com.versilistyson.welldone.data.remote.WellDoneApi
import com.versilistyson.welldone.data.remote.dto.PumpResponse
import com.versilistyson.welldone.util.BASE_URL
import com.versilistyson.welldone.util.StandardClient
import retrofit2.Response

class DashboardRepository {

    val dashboardClient = StandardClient<WellDoneApi>(BASE_URL).create(WellDoneApi::class.java)

    suspend fun fetchAllPumps(): Response<List<PumpResponse>> {
        return dashboardClient.getPumps()
    }

    suspend fun fetchAllSensors(): Response<List<PumpResponse>> {
        return dashboardClient.getSensors()
    }
}