package com.versilistyson.welldone.data.api.log

import com.squareup.moshi.Json
import com.versilistyson.welldone.data.db.log.LogImageData
import com.versilistyson.welldone.data.util.Mappable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface LogImageApi {

    @GET("/api/logs/images/image/{logImageId}")
    suspend fun getImagesByLogId(@Path("logImageId") logImageId: Long): Response<List<Dto.LogImage>>

    //make sure the LogImage has a log id attached to it
    @Multipart
    @POST("/api/logs/images")
    suspend fun addImageDetailsToLog(
        @Part("file") file: MultipartBody.Part,
        @Part metaData: Dto.LogImageMeta
    ): Response<Dto.LogImage>

//    @PUT("/api/logs/images")
//    suspend fun updateLogImage(@Path("imageId") imageId: Long): Response<Any>

    @DELETE("/api/logs/images")
    suspend fun deleteLogImage(@Path("imageId") imageId: Long): Response<Any>

    sealed class Dto {
        data class LogImageMeta (
            @Json(name = "caption") val caption: String,
            @Json(name = "log_id") val logId: Long
        ): Dto()

        data class LogImage (
            @Json(name = "id") val imageId: Long,
            @Json(name = "log_id") val logId: Long,
            @Json(name = "image_url") val imageUrl: String,
            @Json(name = "caption") val caption: String
        ): Dto(), Mappable<LogImageData>{
            override fun map(): LogImageData =
                LogImageData(
                    imageLink = imageUrl,
                    caption = caption,
                    logId = logId
                )
        }
    }
}