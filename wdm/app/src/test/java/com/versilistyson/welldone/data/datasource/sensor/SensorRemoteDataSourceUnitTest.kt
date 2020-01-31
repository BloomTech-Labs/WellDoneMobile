package com.versilistyson.welldone.data.datasource.sensor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.api.sensor.SensorApi
import com.versilistyson.welldone.test_util.BaseCoroutineTest
import com.versilistyson.welldone.test_util.builder.sensor.SensorRecentResponseTestBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SensorRemoteDataSourceUnitTest: BaseCoroutineTest() {

    private val mockSensorApi: SensorApi = mock()

    @Test
    fun `Should return wrapped response of list containing a single sensor`() = testScope.runBlockingTest{
       //SETUP
        val sensorResponseList = listOf(SensorRecentResponseTestBuilder.withDefaults())
        val expected = Response.success(sensorResponseList)
        val result: Response<List<SensorApi.Dto.SensorRecentResponse>>
        whenever(mockSensorApi.getSensors()).thenReturn(expected)
        val sensorRemoteDataSource = SensorRemoteDataSourceImpl(mockSensorApi)
        //EXECUTE
        result = sensorRemoteDataSource.getSensors()
        //CHECK
        verify(mockSensorApi).getSensors()
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Should return wrapped response of a list of multiple sensors`() = testScope.runBlockingTest {
        // SETUP
        val sensorResponseList = listOf(
            SensorRecentResponseTestBuilder(sensorId = 1).build(),
            SensorRecentResponseTestBuilder(sensorId = 2).build()
        )
        val expected = Response.success(sensorResponseList)
        whenever(mockSensorApi.getSensors()).thenReturn(expected)
        val sensorRemoteDataSource = SensorRemoteDataSourceImpl(mockSensorApi)
        val result: Response<List<SensorApi.Dto.SensorRecentResponse>>
        // EXECUTE
        result = sensorRemoteDataSource.getSensors()
        // CHECK
        verify(mockSensorApi).getSensors()
        expected shouldBeEqualTo result
    }
}