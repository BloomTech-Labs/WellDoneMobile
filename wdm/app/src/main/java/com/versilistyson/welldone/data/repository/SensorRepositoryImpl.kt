package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository

class SensorRepositoryImpl(private val sensorApi: SensorApi,
                           private val sensorDao: SensorDao) : SensorRepository {

    //need to map all the DTO objects to entity objects and then return results

    override suspend fun fetchAllSensorsRemotely(): Result<List<Entity.Sensor>?> {
        try {
            response = sensorApi.getSensors()
            val mappedResponse = mutableListOf<Entity.Sensor>()
            for (sensor in response.body()!!) {
                mappedResponse.add(sensor.map())
            }
            Result.Success(mappedResponse)
        } catch(e: Exception){
            Result.NetworkError(response.code(), e)
        }
    }

    override suspend fun fetchAllSensorsLocally(): Result<Entity.Sensors?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchSensorLocally(): Result<Entity.Sensors?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Result<List<Entity.Sensor>?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}