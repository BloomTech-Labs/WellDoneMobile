package com.versilistyson.welldone.data.api.log

import com.squareup.moshi.Json
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface LogImageApi {

    @GET("/api/logs/images")
    suspend fun getLogImages(@Path("logId") logId: Long): Response<List<LogImage>>

    //make sure the LogImage has a log id attached to it
    @Multipart
    @POST("/api/logs/images")
    suspend fun addImagesToLog(
        @Part("file") file: MultipartBody.Part,
        @Body metaData: LogImage): Response<Any>

    @PUT("/api/logs/images")
    suspend fun updateLogImage(@Path("imageId") imageId: Long): Response<Any>

    @DELETE("/api/logs/images")
    suspend fun deleteLogImages()

    data class LogImage(
        @Json(name = "caption") val caption: String,
        @Json(name = "log_id") val logId: Long
    )
}