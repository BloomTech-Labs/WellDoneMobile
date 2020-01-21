package com.versilistyson.welldone.domain.framework.entity

import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng
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
        val wellDepth: Double,
        val location: LatLng,
        val padCounts: PadCounts,
        val padSeconds: PadSeconds
    ): Entity(), Serializable {

        data class PadCounts (
            val padCount0: Double?,
            val padCount1: Double?,
            val padCount2: Double?,
            val padCount3: Double?
        )

        data class PadSeconds (
            val padSeconds0: Double?,
            val padSeconds1: Double?,
            val padSeconds2: Double?,
            val padSeconds3: Double?
        )
    }

    data class Log(
        val dateFiled: String,
        val lastModified: String,
        val status: Drawable,
        val comment: String
    ) : Entity(), Serializable

    data class Sensors(
        val allSensors: List<Sensor>,
        val avgLatLng: LatLng
    )
}