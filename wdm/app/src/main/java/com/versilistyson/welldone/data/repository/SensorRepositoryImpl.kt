package com.versilistyson.welldone.data.repository

import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import retrofit2.Response

class SensorRepositoryImpl(private val remoteDataSource: SensorRemoteDataSource,
                           private val localDataSource: SensorLocalDataSource) : SensorRepository {

    //need to map all the DTO objects to entity objects and then return results

    override suspend fun fetchAllSensorsRemotely(): Response<Entity.Sensors> {
        //first map all remote sensor DTO objects to entity objects, while calculating the average latlng, then create a "Sensors" obj
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchAllSensorsLocally(): Response<Entity.Sensors> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchSensorLocally(): Response<Entity.Sensors> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveAllSensorsLocally(sensors: List<Entity.Sensor>): Response<List<Entity.Sensor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateSensor(sensor: Entity.Sensor): Response<Entity.Sensor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}