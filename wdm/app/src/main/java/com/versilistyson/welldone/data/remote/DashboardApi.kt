package com.versilistyson.welldone.data.remote

import com.versilistyson.welldone.data.remote.dto.PumpResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {

    @GET("api/pumps")
    suspend fun getPumps(): Response<List<PumpResponse>>

    @GET("api/sensors")
    suspend fun getSensors(): Response<List<PumpResponse>>

}