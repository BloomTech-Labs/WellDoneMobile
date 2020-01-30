package com.versilistyson.welldone.data.api

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.util.Mappable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LogApi {

    @GET("api/log/{sensorId}")
    suspend fun getLogs(@Path("sensorId") sensorId: Long): Response<List<Dto.Log>>

    sealed class Dto {
        class Log(
            @Json(name = "id") val logId: Long,
            @Json(name = "sensor_id") val sensorId: Long,
            @Json(name = "date_filed") val dateFiled: String,
            @Json(name = "last_modified") val lastModified: String,
            @Json(name = "status") val status: Int,
            @Json(name = "comment") val comment: String,
            @Json(name = "pictures") val pictures: List<String>,
            @Json(name = "operator_id") val operatorId: Long
        ): Mappable<LogData>, Dto() {
            override fun map(): LogData =
                LogData(
                    logId = logId,
                    sensorId = sensorId,
                    dateFiled = dateFiled,
                    lastModified = lastModified,
                    status = status,
                    comment = comment,
                    pictures = pictures,
                    operatorId = operatorId
                )
        }
    }

}