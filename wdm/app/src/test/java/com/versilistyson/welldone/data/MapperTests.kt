package com.versilistyson.welldone.data

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.api.user.UserAuthenticationApi
import com.versilistyson.welldone.data.db.sensor.SensorData
import junit.framework.Assert.assertEquals
import org.junit.Test

class MapperTests {

    @Test
    fun testSensorDtoSensorDataMapping(){
        val sensorRecentResponse = SensorApi.Dto.SensorRecentResponse(
            5, 0, "01/05/2019", "Kalkuth", "Kcommune",
            "Kalkata", "village", 5.64, 3.8, 6.7, 3.4, 1.0,
            2.0, 3.5, 5.6, 2.4, 3.2, 1.6)
        val sensorData = sensorRecentResponse.map()

        assertEquals(sensorData.sensorId, sensorRecentResponse.sensorId)
        assertEquals(sensorData.sensorStatus, sensorRecentResponse.sensorStatus)
        assertEquals(sensorData.lastUploadDate, sensorRecentResponse.lastUploadDate)
        assertEquals(sensorData.districtName, sensorRecentResponse.districtName)
        assertEquals(sensorData.commune, sensorRecentResponse.commune)
        assertEquals(sensorData.province, sensorRecentResponse.province)
        assertEquals(sensorData.village, sensorRecentResponse.village)
        assertEquals(sensorData.latitude, sensorRecentResponse.latitude)
        assertEquals(sensorData.longitude, sensorRecentResponse.longitude)
        assertEquals(sensorData.padCount0, sensorRecentResponse.padCount0)
        assertEquals(sensorData.padCount1, sensorRecentResponse.padCount1)
        assertEquals(sensorData.padCount2, sensorRecentResponse.padCount2)
        assertEquals(sensorData.padCount3, sensorRecentResponse.padCount3)
        assertEquals(sensorData.padSeconds0, sensorRecentResponse.padSeconds0)
        assertEquals(sensorData.padSeconds1, sensorRecentResponse.padSeconds1)
        assertEquals(sensorData.padSeconds2, sensorRecentResponse.padSeconds2)
        assertEquals(sensorData.padSeconds3, sensorRecentResponse.padSeconds3)
    }

    @Test
    fun testSensorDataSensorEntityMapping(){
        val sensorData = SensorData(5, 0, "01/05/2019", "Kalkuth", "Kcommune",
            "Kalkata", "village", 5.64, 3.8, 6.7, 3.4, 1.0,
            2.0, 3.5, 5.6, 2.4, 3.2, 1.6)

        val sensorEntity = sensorData.map()

        assertEquals(sensorEntity.sensorId, sensorData.sensorId)
        assertEquals(sensorEntity.sensorStatus, sensorData.sensorStatus)
        assertEquals(sensorEntity.lastUploadDate, sensorData.lastUploadDate)
        assertEquals(sensorEntity.districtName, sensorData.districtName)
        assertEquals(sensorEntity.commune, sensorData.commune)
        assertEquals(sensorEntity.province, sensorData.province)
        assertEquals(sensorEntity.village, sensorData.village)
        assertEquals(sensorEntity.location.latitude, sensorData.latitude)
        assertEquals(sensorEntity.location.longitude, sensorData.longitude)
        assertEquals(sensorEntity.padCounts.padCount0, sensorData.padCount0)
        assertEquals(sensorEntity.padCounts.padCount1, sensorData.padCount1)
        assertEquals(sensorEntity.padCounts.padCount2, sensorData.padCount2)
        assertEquals(sensorEntity.padCounts.padCount3, sensorData.padCount3)
        assertEquals(sensorEntity.padSeconds.padSeconds0, sensorData.padSeconds0)
        assertEquals(sensorEntity.padSeconds.padSeconds1, sensorData.padSeconds1)
        assertEquals(sensorEntity.padSeconds.padSeconds2, sensorData.padSeconds2)
        assertEquals(sensorEntity.padSeconds.padSeconds3, sensorData.padSeconds3)
    }
}