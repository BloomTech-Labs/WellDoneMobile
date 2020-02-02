package com.versilistyson.welldone.domain.framework.entity

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

sealed class Entity {
    data class AuthenticationDetails(
        val token: String
    )
    data class UserDetails(
        val userId: Long,
        val firstName: String,
        val lastName: String,
        val emailAddress: String?,
        val phoneNumber: String?
    ): Entity()

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

    data class LogDetails(
        val logId: Long,
        val sensorId: Long,
        val dateFiled: String,
        val lastModified: String,
        val status: Int?,
        val comment: String,
        val operatorId: Long,
        val logImages: List<String>?
    ) : Entity(), Serializable

    data class Sensors(
        val allSensors: List<Sensor>,
        val avgLatLng: LatLng
    )
}