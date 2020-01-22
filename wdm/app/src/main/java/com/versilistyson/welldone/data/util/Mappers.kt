package com.versilistyson.welldone.data.util

import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.api.UserApi
import com.versilistyson.welldone.domain.framework.entity.Entity

fun SensorApi.Dto.SensorRecentResponse.map() =
    Entity.Sensor(
        sensorId = sensorId,
        sensorStatus = sensorStatus,
        lastUploadDate = lastUploadDate,
        districtName = districtName,
        commune = commune,
        province = province,
        village = village,
        wellDepth = wellDepth,
        location = LatLng(latitude, longitude),
        padCounts = Entity.Sensor.PadCounts(padCount0, padCount1, padCount2, padCount3),
        padSeconds = Entity.Sensor.PadSeconds(padSeconds0, padSeconds1, padSeconds2, padSeconds3)
    )

fun UserApi.Dto.AuthenticationResponse.map() =
    Entity.AuthenticatedUser(
        token = authToken,
        userId = userId
    )