package com.versilistyson.welldone.data.api.log

import com.squareup.moshi.Json
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface LogImageApi {

    //make sure the LogImage has a log id attached to it
    @Multipart
    @POST("/api/logs/images")
    suspend fun addImagesToLog(
        @Part("file") file: MultipartBody.Part,
        @Part metaData: LogImage): Response<AddedLogImage>

    @PUT("/api/logs/images")
    suspend fun updateLogImage(@Path("imageId") imageId: Long): Response<Any>

    @DELETE("/api/logs/images")
    suspend fun deleteLogImage(@Path("imageId") imageId: Long): Response<Any>

    data class LogImage(
        @Json(name = "caption") val caption: String,
        @Json(name = "log_id") val logId: Long
    )

    data class AddedLogImage (
        @Json(name = "id") val imageId: Long,
        @Json(name = "log_id") val logId: Long,
        @Json(name = "secure_url") val imageUrl: String,
        @Json(name = "caption") val caption: String
    )
}