package com.versilistyson.welldone.data.datasource.sensor

import androidx.lifecycle.LiveData
import com.versilistyson.welldone.data.api.SensorApi
import com.versilistyson.welldone.data.datasource.BaseDataSource
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Result

class SensorDataSource(private val api: SensorApi) : ISensorDataSource, BaseDataSource() {

    // BaseDataSource absract function
    override fun <T> getResultByDatabase(): LiveData<Result<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun <T> saveResultToDatabase(): Result<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    
    override suspend fun fetchSensorsByNetwork(): Result<List<SensorApi.Dto.SensorRecentResponse>> = getResultByNetwork{ api.getSensors()}
    override suspend fun fetchSensorsByDatabase(): LiveData<Result<List<SensorData.Sensor>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override suspend fun saveSensorsLocally() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    // Ones actually repository
    override suspend fun fetchSensorsWhileOffline() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchSensorsWhileOnline() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}