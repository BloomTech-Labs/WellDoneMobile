package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository

class SensorRepositoryImpl(private val sensorApi: SensorApi,
                           private val sensorDao: SensorDao): SensorRepository, BaseRepository<SensorApi.Dto.SensorRecentResponse,
                                                              SensorData, Entity.Sensor>() {

    //need to map all the DTO objects to entity objects and then return results
    override suspend fun fetchAllSensorsRemotely(): Result<List<Entity.Sensor>> =
        fetchNetworkObjects(sensorApi::getSensors, sensorDao::saveAll)

    override suspend fun fetchAllSensorsLocally(): Result<List<Entity.Sensor>> =
        fetchLocalObjects(sensorDao::getAll)

    override suspend fun fetchSensorLocally(sensorId: Long): Result<Entity.Sensor> =
        fetchLocalObject(sensorId, sensorDao::getSensorBySensorId)

    override suspend fun saveAllSensorsLocally(sensors: List<SensorData>): Result<List<Entity.Sensor>> =
        saveLocalObjects(sensors, sensorDao::saveAll)

    override suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}