package com.versilistyson.welldone.data.api

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET

interface SensorApi {
    @GET("api/sensors/recent")
    suspend fun getSensors(): Response<List<Dto.SensorRecentResponse>>

    sealed class Dto {
        data class SensorRecentResponse(
            @Json(name = "pump_index") val sensorId: Int,
            @Json(name = "status") val sensorStatus: Int?,
            @Json(name = "data_finished") val lastUploadDate: String,
            @Json(name = "district_name") val districtName: String,
            @Json(name = "commune_name") val commune: String,
            @Json(name = "province_name") val province: String,
            @Json(name = "village_name") val village: String,
            @Json(name = "latitude") val latitude: Double,
            @Json(name = "longitude") val longitude: Double,
            @Json(name = "depth") val wellDepth: Int,
            @Json(name = "pad_count_0") val padCount0: Double?,
            @Json(name = "pad_count_1") val padCount1: Double?,
            @Json(name = "pad_count_2") val padCount2: Double?,
            @Json(name = "pad_count_3") val padCount3: Double?,
            @Json(name = "pad_seconds_0") val padSeconds0: Double?,
            @Json(name = "pad_seconds_1") val padSeconds1: Double?,
            @Json(name = "pad_seconds_2") val padSeconds2: Double?,
            @Json(name = "pad_seconds_3") val padSeconds3: Double?
            /*@Json(name = "date") val date: String?,*/
            //@Json(name = "created_at") val dateCreated: String,
            // @Json(name = "bluetooth") val bluetooth: Int?,
            //@Json(name = "cellular") val cellular: Int?,
            //@Json(name = "count") val count: Int?,
            //@Json(name = "headquarter_city") val headquarter_city: String,
            // [mightChange] @Json(name = "history_index") val history_index: Int,
            //@Json(name = "kind") val kind: String?,
            //@Json(name = "org_id") val org_id: Int,
            //@Json(name = "org_name") val org_name: String,
            //@Json(name = "physical_id") val physical_id: Int,
            //@Json(name = "quality") val quality: String?,
            //@Json(name = "remark") val remark: String?,
            //@Json(name = "reported_percent") val reported_percent: Double?,
            //@Json(name = "sensor_id") val sensor_id: Int,
            //@Json(name = "sensor_index") val sensor_index: Int,
            //@Json(name = "sensor_pid") val sensor_pid: Int,
            //@Json(name = "static") val static: Double?,
            //@Json(name = "total") val total: Double?,
            //@Json(name = "training") val training: String?,
            //@Json(name = "type") val type: String?,
            //@Json(name = "yield") val yield: Double?
        ) : Dto()
    }
}