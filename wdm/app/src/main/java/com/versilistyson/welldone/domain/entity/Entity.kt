package com.versilistyson.welldone.domain.entity

import android.graphics.drawable.Drawable
import java.io.Serializable

sealed class Entity {

    data class AuthenticatedUser(
        val token: String,
        val userId: Int
    ) : Entity()

    data class Sensor(
        val sensorId: Int,
        val sensorStatus: Int?,
        val lastUploadDate: String,
        val districtName: String,
        val commune: String,
        val province: String,
        val village: String,
        val latitude: Double,
        val longitude: Double,
        val wellDepth: Int,
        val padCount0: Double?,
        val padCount1: Double?,
        val padCount2: Double?,
        val padCount3: Double?,
        val padSeconds0: Double?,
        val padSeconds1: Double?,
        val padSeconds2: Double?,
        val padSeconds3: Double?
    ): Entity()

    data class Log(
        val dateFiled: String,
        val lastModified: String,
        val status: Drawable,
        val comment: String
    ) : Entity(), Serializable
}