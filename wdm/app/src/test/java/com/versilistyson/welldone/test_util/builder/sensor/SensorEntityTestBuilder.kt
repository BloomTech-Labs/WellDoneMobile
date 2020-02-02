package com.versilistyson.welldone.test_util.builder.sensor

import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.domain.framework.entity.Entity

data class SensorEntityTestBuilder(
    private val sensorId: Int = DEFAULT_SENSOR_ID,
    private val sensorStatus: Int? = DEFAULT_SENSOR_STATUS,
    private val lastUploadDate: String = DEFAULT_LAST_UPLOAD_DATE,
    private val districtName: String = DEFAULT_DISTRICT_NAME,
    private val commune: String = DEFAULT_COMMUNE,
    private val province: String = DEFAULT_PROVINCE,
    private val village: String = DEFAULT_VILLAGE,
    private val wellDepth: Double = DEFAULT_WELL_DEPTH,
    private val location: LatLng = DEFAULT_LAT_LNG,
    private val padCounts: Entity.Sensor.PadCounts = DEFAULT_PAD_COUNTS,
    private val padSeconds: Entity.Sensor.PadSeconds = DEFAULT_PAD_SECONDS
) {
    fun build() = Entity.Sensor(sensorId, sensorStatus, lastUploadDate, districtName, commune, province, village, wellDepth, location, padCounts, padSeconds)
    companion object {
        private const val DEFAULT_SENSOR_ID = 1
        private const val DEFAULT_SENSOR_STATUS = 3
        private const val DEFAULT_LAST_UPLOAD_DATE = "11/11/11"
        private const val DEFAULT_DISTRICT_NAME = "district"
        private const val DEFAULT_COMMUNE = "commune"
        private const val DEFAULT_PROVINCE = "province"
        private const val DEFAULT_VILLAGE = "village"
        private const val DEFAULT_WELL_DEPTH = 1.0
        private val DEFAULT_LAT_LNG = LatLng(1.0, 1.0)
        private val DEFAULT_PAD_COUNTS = Entity.Sensor.PadCounts(
            1.0,1.0,1.0,1.0
        )
        private val NULL_PAD_COUNTS = Entity.Sensor.PadCounts(null, null, null, null)
        private val DEFAULT_PAD_SECONDS = Entity.Sensor.PadSeconds(1.0, 1.0, 1.0, 1.0)
        private val NULL_PAD_SECONDS = Entity.Sensor.PadSeconds(null, null, null, null)

        fun withDefaults(): SensorEntityTestBuilder {
            return SensorEntityTestBuilder()
        }
        fun withEmptyPadCounts(): SensorEntityTestBuilder {
            return SensorEntityTestBuilder(
                padCounts = NULL_PAD_COUNTS
            )
        }
        fun withEmptyPadSeconds(): SensorEntityTestBuilder {
            return SensorEntityTestBuilder(
                padSeconds = NULL_PAD_SECONDS
            )
        }
        fun withNullSensorStatus(): SensorEntityTestBuilder {
            return SensorEntityTestBuilder(
                sensorStatus = null
            )
        }
    }
}

