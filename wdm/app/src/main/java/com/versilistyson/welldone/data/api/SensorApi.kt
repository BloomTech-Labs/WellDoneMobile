package com.versilistyson.welldone.data.api

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.remote.dto.SensorRecentResponse
import retrofit2.Response
import retrofit2.http.GET

interface SensorApi {
    @GET("api/sensors/recent")
    suspend fun getSensors(): Response<List<SensorRecentResponse>>

    sealed class Dto {
        data class SensorRecentResponse(
            @Json(name = "bluetooth") val bluetooth: Int?,
            @Json(name = "cellular") val cellular: Int?,
            @Json(name = "commune_name") val commune_name: String,
            @Json(name = "count") val count: Int?,
            @Json(name = "created_at") val created_at: String,
            @Json(name = "data_finished") val data_finished: String,
            @Json(name = "date") val date: String?,
            @Json(name = "depth") val depth: Int,
            @Json(name = "district_name") val district_name: String,
            @Json(name = "headquarter_city") val headquarter_city: String,
            @Json(name = "history_index") val history_index: Int,
            @Json(name = "kind") val kind: String?,
            @Json(name = "latitude") val latitude: Double,
            @Json(name = "longitude") val longitude: Double,
            @Json(name = "org_id") val org_id: Int,
            @Json(name = "org_name") val org_name: String,
            @Json(name = "pad_count_0") val pad_count_0: Double?,
            @Json(name = "pad_count_1") val pad_count_1: Double?,
            @Json(name = "pad_count_2") val pad_count_2: Double?,
            @Json(name = "pad_count_3") val pad_count_3: Double?,
            @Json(name = "pad_seconds_0") val pad_seconds_0: Double?,
            @Json(name = "pad_seconds_1") val pad_seconds_1: Double?,
            @Json(name = "pad_seconds_2") val pad_seconds_2: Double?,
            @Json(name = "pad_seconds_3") val pad_seconds_3: Double?,
            @Json(name = "physical_id") val physical_id: Int,
            @Json(name = "province_name") val province_name: String,
            @Json(name = "pump_index") val pump_index: Int,
            @Json(name = "quality") val quality: String?,
            @Json(name = "remark") val remark: String?,
            @Json(name = "reported_percent") val reported_percent: Double?,
            @Json(name = "sensor_id") val sensor_id: Int,
            @Json(name = "sensor_index") val sensor_index: Int,
            @Json(name = "sensor_pid") val sensor_pid: Int,
            @Json(name = "static") val static: Double?,
            @Json(name = "status") val status: Int?,
            @Json(name = "total") val total: Double?,
            @Json(name = "training") val training: String?,
            @Json(name = "type") val type: String?,
            @Json(name = "village_name") val village_name: String,
            @Json(name = "yield") val yield: Double?
        ) : Dto()
    }
}