package com.versilistyson.welldone.test_util.builder.sensor

import com.versilistyson.welldone.data.api.sensor.SensorApi

data class SensorRecentResponseTestBuilder(
    private val sensorId: Int = DEFAULT_SENSOR_ID,
    private val sensorStatus: Int? = DEFAULT_SENSOR_STATUS,
    private val lastUploadDate: String = DEFAULT_LAST_UPLOAD_DATE,
    private val districtName: String = DEFAULT_DISTRICT_NAME,
    private val commune: String = DEFAULT_COMMUNE_NAME,
    private val province: String = DEFAULT_PROVINCE,
    private val village: String = DEFAULT_VILLAGE,
    private val latitude: Double = DEFAULT_LATITUDE,
    private val longitude: Double = DEFAULT_LONGITUDE,
    private val wellDepth: Double = DEFAULT_WELL_DEPTH,
    private val padCount0: Double? = DEFAULT_PADCOUNT_0,
    private val padCount1: Double? = DEFAULT_PADCOUNT_1,
    private val padCount2: Double? = DEFAULT_PADCOUNT_2,
    private val padCount3: Double? = DEFAULT_PADCOUNT_3,
    private val padSeconds0: Double? = DEFAULT_PADSECONDS_0,
    private val padSeconds1: Double? = DEFAULT_PADSECONDS_1,
    private val padSeconds2: Double? = DEFAULT_PADSECONDS_2,
    private val padSeconds3: Double? = DEFAULT_PADSECONDS_3
) {

    companion object {
        private const val DEFAULT_SENSOR_ID = 1
        private const val DEFAULT_SENSOR_STATUS = 3
        private const val DEFAULT_LAST_UPLOAD_DATE = "11/11/11"
        private const val DEFAULT_DISTRICT_NAME = "district"
        private const val DEFAULT_COMMUNE_NAME = "commune"
        private const val DEFAULT_VILLAGE = "village"
        private const val DEFAULT_PROVINCE = "province"
        private const val DEFAULT_LATITUDE = 1.0
        private const val DEFAULT_LONGITUDE = 1.0
        private const val DEFAULT_WELL_DEPTH = 1.0
        private const val DEFAULT_PADCOUNT_0 = 1.0
        private const val DEFAULT_PADCOUNT_1 = 1.0
        private const val DEFAULT_PADCOUNT_2 = 1.0
        private const val DEFAULT_PADCOUNT_3 = 1.0
        private const val DEFAULT_PADSECONDS_0 = 1.0
        private const val DEFAULT_PADSECONDS_1 = 1.0
        private const val DEFAULT_PADSECONDS_2 = 1.0
        private const val DEFAULT_PADSECONDS_3 = 1.0

        fun withDefaults(): SensorApi.Dto.SensorRecentResponse {
            return SensorRecentResponseTestBuilder().build()
        }
        fun withNullPadCountsAndSeconds(): SensorRecentResponseTestBuilder {
            return SensorRecentResponseTestBuilder(padCount0 = null,padSeconds1 = null, padSeconds2 = null, padSeconds3 = null, padSeconds0 = null, padCount3 = null, padCount2 = null, padCount1 = null)
        }

    }
    fun build(): SensorApi.Dto.SensorRecentResponse {
        return SensorApi.Dto.SensorRecentResponse(
            sensorId,
            sensorStatus,
            lastUploadDate,
            districtName,
            commune,
            province,
            village,
            latitude,
            longitude,
            wellDepth,
            padCount0,
            padCount1,
            padCount2,
            padCount3,
            padSeconds0,
            padSeconds1,
            padSeconds2,
            padCount3
        )
    }
}