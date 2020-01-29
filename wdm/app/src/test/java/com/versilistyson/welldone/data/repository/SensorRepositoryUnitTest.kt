package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.StoreKey
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
import com.versilistyson.welldone.test_util.builder.sensor.SensorDataTestBuilder
import com.versilistyson.welldone.test_util.builder.sensor.SensorRecentResponseTestBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.After
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

    @Test
    fun `Fetch fresh sensors should return a StoreResponse of Flow of List of SensorData`() {
        try {
            runBlocking {

                val sensorRepository =
                    SensorRepositoryImpl(mockLocalSensorDataSource, mockRemoteSensorDataSource)

                whenever(mockRemoteSensorDataSource.getSensors()).thenReturn(Response.success(sensorDtoList))
                whenever(mockLocalSensorDataSource.saveSensors(StoreKey.SensorsKey(), sensorDataList)).thenReturn(Unit)
                whenever(mockLocalSensorDataSource.getSensors(StoreKey.SensorsKey())).thenReturn(flowOf(sensorDataList))

                val result: Flow<StoreResponse<List<SensorData>>>
                var resultDataValue: List<SensorData>
                var resultOriginValue: ResponseOrigin
                val expectedOriginValue = ResponseOrigin.Fetcher

                //EXECUTE
                result = sensorRepository.fetchFreshSensors()

                result.collect { value ->
                    when (value) {
                        is StoreResponse.Data -> {
                            resultDataValue = value.requireData()
                            resultOriginValue = value.origin

                            resultDataValue shouldBeEqualTo sensorDataList
                            resultDataValue.size shouldNotBeEqualTo  0
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