package com.versilistyson.welldone.data

import com.versilistyson.welldone.data.api.sensor.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorData
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class MapperTests {

    @Test
    fun testSensorDtoSensorDataMapping(){
        val sensorRecentResponse = SensorApi.Dto.SensorRecentResponse(
            5, 0, "01/05/2019", "Kalkuth", "Kcommune",
            "Kalkata", "village", 5.64, 3.8, 6.7, 3.4, 1.0,
            2.0, 3.5, 5.6, 2.4, 3.2, 1.6)
        val sensorData = sensorRecentResponse.map()

        sensorData.sensorId shouldBeEqualTo sensorRecentResponse.sensorId
        sensorData.sensorStatus shouldBeEqualTo sensorRecentResponse.sensorStatus
        sensorData.lastUploadDate shouldBeEqualTo sensorRecentResponse.lastUploadDate
        sensorData.districtName shouldBeEqualTo sensorRecentResponse.districtName
        sensorData.commune shouldBeEqualTo sensorRecentResponse.commune
        sensorData.province shouldBeEqualTo sensorRecentResponse.province
        sensorData.village shouldBeEqualTo sensorRecentResponse.village
        sensorData.latitude shouldBeEqualTo sensorRecentResponse.latitude
        sensorData.longitude shouldBeEqualTo sensorRecentResponse.longitude
        sensorData.padCount0 shouldBeEqualTo sensorRecentResponse.padCount0
        sensorData.padCount1 shouldBeEqualTo sensorRecentResponse.padCount1
        sensorData.padCount2 shouldBeEqualTo sensorRecentResponse.padCount2
        sensorData.padCount3 shouldBeEqualTo sensorRecentResponse.padCount3
        sensorData.padSeconds0 shouldBeEqualTo sensorRecentResponse.padSeconds0
        sensorData.padSeconds1 shouldBeEqualTo sensorRecentResponse.padSeconds1
        sensorData.padSeconds2 shouldBeEqualTo sensorRecentResponse.padSeconds2
        sensorData.padSeconds3 shouldBeEqualTo sensorRecentResponse.padSeconds3
    }

    @Test
    fun testSensorDataSensorEntityMapping(){
        val sensorData = SensorData(5, 0, "01/05/2019", "Kalkuth", "Kcommune",
            "Kalkata", "village", 5.64, 3.8, 6.7, 3.4, 1.0,
            2.0, 3.5, 5.6, 2.4, 3.2, 1.6)

        val sensorEntity = sensorData.map()

        sensorEntity.sensorId shouldBeEqualTo sensorData.sensorId
        sensorEntity.sensorStatus shouldBeEqualTo sensorData.sensorStatus
        sensorEntity.lastUploadDate shouldBeEqualTo sensorData.lastUploadDate
        sensorEntity.districtName shouldBeEqualTo sensorData.districtName
        sensorEntity.commune shouldBeEqualTo sensorData.commune
        sensorEntity.province shouldBeEqualTo sensorData.province
        sensorEntity.village shouldBeEqualTo sensorData.village
        sensorEntity.location.latitude shouldBeEqualTo sensorData.latitude
        sensorEntity.location.longitude shouldBeEqualTo sensorData.longitude
        sensorEntity.padCounts.padCount0 shouldBeEqualTo sensorData.padCount0
        sensorEntity.padCounts.padCount1 shouldBeEqualTo sensorData.padCount1
        sensorEntity.padCounts.padCount2 shouldBeEqualTo sensorData.padCount2
        sensorEntity.padCounts.padCount3 shouldBeEqualTo sensorData.padCount3
        sensorEntity.padSeconds.padSeconds0 shouldBeEqualTo sensorData.padSeconds0
        sensorEntity.padSeconds.padSeconds1 shouldBeEqualTo sensorData.padSeconds1
        sensorEntity.padSeconds.padSeconds2 shouldBeEqualTo sensorData.padSeconds2
        sensorEntity.padSeconds.padSeconds3 shouldBeEqualTo sensorData.padSeconds3
    }
}