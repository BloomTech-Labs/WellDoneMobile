package com.versilistyson.welldone.domain.framework.entity

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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

    @Parcelize
    data class Sensor(
        val sensorId: Int,
        val sensorStatus: Int?,
        val lastUploadDate: String,
        val districtName: String,
        val commune: String,
        val province: String,
        val village: String,
        val wellDepth: Double,
        val location: LatLng): Entity(), Parcelable

    @Parcelize
    data class LogDetails(
        val logId: Long,
        val dateFiled: String,
        val lastModified: String,
        val status: Int?,
        val comment: String,
        val logImages: List<String>?
    ) : Entity(), Parcelable

    data class Sensors(
        val allSensors: List<Sensor>,
        val avgLatLng: LatLng
    )
}