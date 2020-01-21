package com.versilistyson.welldone.data

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.api.UserApi
import com.versilistyson.welldone.data.mappers.AuthResponseToAuthUserMapper
import com.versilistyson.welldone.data.mappers.SensorDtoSensorEntityMapper
import junit.framework.Assert.assertEquals
import org.junit.Test

class MapperTests {

    @Test
    fun testSensorDtoSensorEntityMapping(){
        val sensorDataSensorEntityMapper = SensorDtoSensorEntityMapper()
        val sensorRecentResponse = SensorApi.Dto.SensorRecentResponse(
            5, 0, "01/05/2019", "Kalkuth", "Kcommune",
            "Kalkata", "village", 5.64, 3.8, 6.7, 3.4, 1.0,
            2.0, 3.5, 5.6, 2.4, 3.2, 1.6)
        val sensorEntity = sensorDataSensorEntityMapper.mapFrom(sensorRecentResponse)

        assertEquals(sensorEntity.sensorId, sensorRecentResponse.sensorId)
        assertEquals(sensorEntity.sensorStatus, sensorRecentResponse.sensorStatus)
        assertEquals(sensorEntity.lastUploadDate, sensorRecentResponse.lastUploadDate)
        assertEquals(sensorEntity.districtName, sensorRecentResponse.districtName)
        assertEquals(sensorEntity.commune, sensorRecentResponse.commune)
        assertEquals(sensorEntity.province, sensorRecentResponse.province)
        assertEquals(sensorEntity.village, sensorRecentResponse.village)
        assertEquals(sensorEntity.location.latitude, sensorRecentResponse.latitude)
        assertEquals(sensorEntity.location.longitude, sensorRecentResponse.longitude)
        assertEquals(sensorEntity.padCounts.padCount0, sensorRecentResponse.padCount0)
        assertEquals(sensorEntity.padCounts.padCount1, sensorRecentResponse.padCount1)
        assertEquals(sensorEntity.padCounts.padCount2, sensorRecentResponse.padCount2)
        assertEquals(sensorEntity.padCounts.padCount3, sensorRecentResponse.padCount3)
        assertEquals(sensorEntity.padSeconds.padSeconds0, sensorRecentResponse.padSeconds0)
        assertEquals(sensorEntity.padSeconds.padSeconds1, sensorRecentResponse.padSeconds1)
        assertEquals(sensorEntity.padSeconds.padSeconds2, sensorRecentResponse.padSeconds2)
        assertEquals(sensorEntity.padSeconds.padSeconds3, sensorRecentResponse.padSeconds3)
    }

    @Test
    fun testAuthResponseToAuthUserEntityMapping(){
        val authResponseToAuthUserMapper = AuthResponseToAuthUserMapper()
        val authResponse = UserApi.Dto.AuthenticationResponse("random_token", 5)
        val authUser = authResponseToAuthUserMapper.mapFrom(authResponse)

        assertEquals(authUser.token, authResponse.authToken)
        assertEquals(authUser.userId, authResponse.userId)
    }
}