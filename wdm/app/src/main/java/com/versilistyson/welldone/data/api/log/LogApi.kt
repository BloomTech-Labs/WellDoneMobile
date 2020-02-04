package com.versilistyson.welldone.data.api.log

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.db.log.LogData
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.util.Mappable
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface LogApi {

    @GET("/api/logs/images/{sensorId}")
    suspend fun getAllLogs(): Response<List<Dto.Log>>

    @POST("api/logs")
    suspend fun addLog(@Body newLog: Dto.LogToPost): Response<Any>

    @PUT("api/logs/{logId}")
    suspend fun updateLog(
        @Path("logId") logId: Long,
        @Body @Json(name = "comment") comment: String
    ): Response<Dto.Log>

    @DELETE("api/logs/log")
    suspend fun deleteLog(@Path("logId") logId: Long): Response<Dto.Log>

    sealed class Dto {
        data class Log(
            @Json(name = "id") val logId: Long,
            @Json(name = "date_filed") val dateFiled: String,
            @Json(name = "last_modified") val lastModified: String,
            @Json(name = "status") val status: Int?,
            @Json(name = "comment") val comment: String,
            @Json(name = "operator_id") val operatorId: Long,
            @Json(name = "sensor_id") val sensorId: Long,
            @Json(name = "org_name") val organizationName: String
        ) : Mappable<LogData> {
            override fun map(): LogData =
                LogData(
                    logId = logId,
                    dateFiled = dateFiled,
                    lastModified = lastModified,
                    status = status,
                    comment = comment,
                    operatorId = operatorId,
                    sensorId = sensorId,
                    organizationName = organizationName
                )
        }

        data class LogToPost(
            @Json(name = "sensor_id") val sensorId: Long,
            @Json(name = "comment") val comment: String
        ) : Dto()
    }

}