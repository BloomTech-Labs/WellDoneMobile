package com.versilistyson.welldone.domain.framework.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.google.android.gms.maps.model.LatLng
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class GetSensorsUseCase(private val sensorRepository: SensorRepository, private val coroutineScope: CoroutineScope) : UseCase<LiveData<Entity.Sensors>, UseCase.None>() {

    private val sensorsLiveData: MutableLiveData<Entity.Sensors> by lazy {
        MutableLiveData<Entity.Sensors>()
    }
    private lateinit var eitherReturnValue: Either<Failure, LiveData<Entity.Sensors>>

    override suspend fun run(params: None): Either<Failure, LiveData<Entity.Sensors>> {
        val sensorCollector = object : FlowCollector<StoreResponse<List<SensorData>>> {
            override suspend fun emit(response: StoreResponse<List<SensorData>>) {
                when (response) {
                    is StoreResponse.Loading -> {
                        eitherReturnValue = Either.Left(Failure.CurrentlyLoading)
                    }
                    is StoreResponse.Data -> {
                        handleData(response.requireData())
                    }
                    is StoreResponse.Error -> {
                        handleError(response.error as Exception, response.origin)
                    }
                }
            }
        }
        coroutineScope.launch {
            sensorRepository.fetchSensors().collect(sensorCollector)
        }
        return eitherReturnValue
    }

    private fun handleData(sensors: List<SensorData>) {
        val mappedSensors = mutableListOf<Entity.Sensor>()
        var totalLatitude = 0.0
        var totalLongitude = 0.0
        sensors.forEach { sensor ->
            mappedSensors.add(sensor.map())
            totalLatitude += sensor.latitude
            totalLongitude += sensor.longitude
        }
        sensorsLiveData.value = Entity.Sensors(
            mappedSensors,
            LatLng(
                totalLatitude / (mappedSensors.size + 1),
                totalLongitude / (mappedSensors.size + 1)
            )
        )
        eitherReturnValue = Either.Right(sensorsLiveData)
    }

    private fun handleError(error: Exception, origin: ResponseOrigin){

        when(origin) {
            ResponseOrigin.Cache -> {}
            ResponseOrigin.Fetcher -> {}
            ResponseOrigin.Persister -> {}
        }
        eitherReturnValue = Either.Left(GetSensorsFailure(error))
    }

    data class GetSensorsFailure(val error: Exception) : Failure.FeatureFailure(error)
}