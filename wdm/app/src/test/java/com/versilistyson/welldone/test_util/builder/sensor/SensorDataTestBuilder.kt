package com.versilistyson.welldone.test_util.builder.sensor

import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.framework.entity.Entity
data class SensorDataTestBuilder(
    private val sensorId: Int = DEFAULT_SENSOR_ID,
    private val sensorStatus: Int? = DEFAULT_SENSOR_STATUS,
    private val lastUpload: String = DEFAULT_LAST_UPLOAD_DATE,
    private val districtName: String = DEFAULT_DISTRICT_NAME,
    private val commune: String = DEFAULT_COMMUNE,
    private val province: String = DEFAULT_PROVINCE,
    private val village: String = DEFAULT_VILLAGE,
    private val latitude: Double = DEFAULT_LATITUDE,
    private val longitude: Double = DEFAULT_LONGITUDE,
    private val wellDepth: Double = DEFAULT_WELL_DEPTH,
    private val padCount0: Double? = DEFAULT_PAD_COUNT_0,
    private val padCount1: Double? = DEFAULT_PAD_COUNT_1,
    private val padCount2: Double? = DEFAULT_PAD_COUNT_2,
    private val padCount3: Double? = DEFAULT_PAD_COUNT_3,
    private val padSeconds0: Double? = DEFAULT_PAD_SECONDS_0,
    private val padSeconds1: Double? = DEFAULT_PAD_SECONDS_1,
    private val padSeconds2: Double? = DEFAULT_PAD_SECONDS_2,
    private val padSeconds3: Double? = DEFAULT_PAD_SECONDS_3
) {
    fun build() = SensorData(
        sensorId,
        sensorStatus,
        lastUpload,
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
        padSeconds3
    )
    companion object {
        private const val DEFAULT_SENSOR_ID = 1
        private const val DEFAULT_SENSOR_STATUS = 1
        private const val DEFAULT_LAST_UPLOAD_DATE = "11/21/19"
        private const val DEFAULT_DISTRICT_NAME = "district"
        private const val DEFAULT_COMMUNE = "commune"
        private const val DEFAULT_PROVINCE = "province"
        private const val DEFAULT_VILLAGE = "village"
        private const val DEFAULT_LATITUDE = 32.0
        private const val DEFAULT_LONGITUDE = 32.0
        private const val DEFAULT_WELL_DEPTH = 100.0
        private const val DEFAULT_PAD_COUNT_0 = 0.0
        private const val DEFAULT_PAD_COUNT_1 = 1.0
        private const val DEFAULT_PAD_COUNT_2 = 2.0
        private const val DEFAULT_PAD_COUNT_3 = 3.0
        private const val DEFAULT_PAD_SECONDS_0 = 0.0
        private const val DEFAULT_PAD_SECONDS_1 = 1.0
        private const val DEFAULT_PAD_SECONDS_2 = 2.0
        private const val DEFAULT_PAD_SECONDS_3 = 3.0

        fun withDefaults(): SensorDataTestBuilder {
            return SensorDataTestBuilder()
        }
        fun withNullSensorStatusAndPadData(id: Int = 1): SensorDataTestBuilder {
            return SensorDataTestBuilder(
                sensorId = id,
                sensorStatus = null,
                padCount0 = null,
                padCount1 = null,
                padCount2 = null,
                padCount3 = null,
                padSeconds0 = null,
                padSeconds1 = null,
                padSeconds2 = null,
                padSeconds3 = null
            )
        }
    }
}