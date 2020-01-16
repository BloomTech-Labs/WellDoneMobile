package com.versilistyson.welldone.data.datasource.sensor

import com.versilistyson.welldone.data.datasource.BaseDataSource
import com.versilistyson.welldone.domain.entity.Entity

interface ISensorApiDataSource : BaseDataSource {
    suspend fun getSensors() : List<Entity.Sensor>
}