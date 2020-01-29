package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.test_util.builder.sensor.SensorDataTestBuilder
import com.versilistyson.welldone.test_util.builder.sensor.SensorRecentResponseTestBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@InternalCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class SensorRepositoryUnitTest {

    private val mockLocalSensorDataSource: SensorLocalDataSource = mock()
    private val mockRemoteSensorDataSource: SensorRemoteDataSource = mock()

    private val sensorDtoList = listOf(
        SensorRecentResponseTestBuilder(sensorId = 1).build(),
        SensorRecentResponseTestBuilder(sensorId = 2).build(),
        SensorRecentResponseTestBuilder(sensorId = 3).build()
    )

    private val sensorDataList = listOf(
        SensorDataTestBuilder(sensorId = 1).build(),
        SensorDataTestBuilder(sensorId = 2).build(),
        SensorDataTestBuilder(sensorId = 3).build()
    )

    @Before
    fun setup() {
        runBlocking {
            whenever(mockRemoteSensorDataSource.getSensors()).thenReturn(Response.success(sensorDtoList))
            whenever(mockLocalSensorDataSource.saveSensors(StoreKey.SensorsKey(), sensorDataList)).thenReturn(Unit)
            whenever(mockLocalSensorDataSource.getSensors(StoreKey.SensorsKey())).thenReturn(flowOf(sensorDataList))
        }
    }

    @Test
    fun `Fetch fresh sensors should return a StoreResponse of Flow of List of SensorData`(){
        try {
            runBlocking {
                val sensorRepository =
                    SensorRepositoryImpl(mockLocalSensorDataSource, mockRemoteSensorDataSource)

                var resultDataValue: List<SensorData>
                var resultOriginValue: ResponseOrigin
                val expectedOriginValue = ResponseOrigin.Fetcher

                //EXECUTE
                val result = sensorRepository.fetchFreshSensors()

                result.collect { value ->
                    when (value) {
                        is StoreResponse.Data -> {
                            resultDataValue = value.requireData()
                            resultOriginValue = value.origin

                            verify(mockRemoteSensorDataSource).getSensors()
                            verify(mockLocalSensorDataSource).saveSensors(
                                StoreKey.SensorsKey(),
                                sensorDataList
                            )
                            verify(mockLocalSensorDataSource).getSensors(StoreKey.SensorsKey())
                            resultDataValue shouldBeEqualTo sensorDataList
                            resultOriginValue shouldBeEqualTo expectedOriginValue

                            this.cancel()
                        }
                    }
                }
            }
        } catch(e: CancellationException){

        }
    }

    @Test
    fun `Fetch sensors should return a StoreResponse of Flow of List of SensorData`() {
        try{
            runBlocking {
                val sensorRepository =
                    SensorRepositoryImpl(mockLocalSensorDataSource, mockRemoteSensorDataSource)

                var resultDataValue: List<SensorData>
                var resultOriginValue: ResponseOrigin
                val expectedOriginValue = ResponseOrigin.Persister

                val result = sensorRepository.fetchSensors()

                result.collect { response ->
                    when (response) {
                        is StoreResponse.Data -> {
                            resultDataValue = response.requireData()
                            resultOriginValue = response.origin

                            resultDataValue shouldBeEqualTo sensorDataList
                            resultOriginValue shouldBeEqualTo expectedOriginValue
                            this.cancel()
                        }
                    }
                }
            }
        } catch(e: CancellationException){

        }
    }
}