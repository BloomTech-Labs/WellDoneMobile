package com.versilistyson.welldone.data.mappers

import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.domain.common.Mapper
import com.versilistyson.welldone.domain.framework.entity.Entity

class SensorDtoSensorEntityMapper: Mapper<SensorApi.Dto.SensorRecentResponse, Entity.Sensor>() {

    override fun mapFrom(from: SensorApi.Dto.SensorRecentResponse) =
        Entity.Sensor(
            from.sensorId,
            from.sensorStatus,
            from.lastUploadDate,
            from.districtName,
            from.commune,
            from.province,
            from.village,
            from.wellDepth,
            LatLng(from.latitude, from.longitude),
            Entity.Sensor.PadCounts(from.padCount0, from.padCount1, from.padCount2, from.padCount3),
            Entity.Sensor.PadSeconds(from.padSeconds0, from.padSeconds1, from.padSeconds2, from.padSeconds3)
        )
}