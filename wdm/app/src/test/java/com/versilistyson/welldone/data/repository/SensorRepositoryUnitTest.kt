package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreRequest
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@FlowPreview
@ExperimentalCoroutinesApi
class SensorRepositoryUnitTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun before() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    private val mockLocalSensorDataSource: SensorLocalDataSource = mock()
    private val mockRemoteSensorDataSource: SensorRemoteDataSource = mock()

    @Test
    fun `Fetch fresh sensors returns a StoreResponse of Flow of List of SensorData`() = testScope.runBlockingTest {

        val sensorRepository =
            SensorRepositoryImpl(mockLocalSensorDataSource, mockRemoteSensorDataSource)

        val sensorDtoList = listOf(
            SensorRecentResponseTestBuilder(sensorId = 1).build(),
            SensorRecentResponseTestBuilder(sensorId = 2).build(),
            SensorRecentResponseTestBuilder(sensorId = 3).build()
        )

        val sensorDataList = listOf(
            SensorDataTestBuilder(sensorId = 1).build(),
            SensorDataTestBuilder(sensorId = 2).build(),
            SensorDataTestBuilder(sensorId = 3).build()
        )

        whenever(mockRemoteSensorDataSource.getSensors()).thenReturn(Response.success(sensorDtoList))
        whenever(mockLocalSensorDataSource.saveSensors(StoreKey.SensorsKey(), sensorDataList)).thenReturn(Unit)
        whenever(mockLocalSensorDataSource.getSensors(StoreKey.SensorsKey())).thenReturn(flowOf(sensorDataList))

        val result: Flow<StoreResponse<List<SensorData>>>
        val expected = flowOf(StoreResponse.Data(sensorDataList, ResponseOrigin.Fetcher))

        //EXECUTE
        result = sensorRepository.fetchFreshSensors()

        verify(mockLocalSensorDataSource).getSensors(StoreKey.SensorsKey())
        verify(mockLocalSensorDataSource).saveSensors(StoreKey.SensorsKey(), sensorDataList)
        expected shouldBeEqualTo result
    }

    @Test
    fun `Should fetch sensors from local database`() {

    }

    @Test
    fun `Should force fetch sensors from api`() {

    }

}