package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.common.Result
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository

class SensorRepositoryImpl(private val remoteDataSource: SensorRemoteDataSource,
                           private val localDataSource: SensorLocalDataSource) : SensorRepository {

    //need to map all the DTO objects to entity objects and then return results
    override suspend fun fetchAllSensorsRemotely(): Result<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchAllSensorsLocally(): Result<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchSensorLocally(): Result<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Result<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateSensor(sensor: Entity.Sensor): Result<Entity.Sensor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}