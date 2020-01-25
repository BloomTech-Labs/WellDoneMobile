package com.versilistyson.welldone.data.datasource.sensor

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.test_util.builder.sensor.SensorDataTestBuilder
import com.versilistyson.welldone.test_util.builder.sensor.SensorEntityTestBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SensorLocalDataSourceUnitTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `Should send back a single sensor entity`() = testScope.runBlockingTest {
        // Setup
        val sensor = SensorDataTestBuilder.withDefaults().build()
        val sensorList = listOf(sensor)
        val mockSensorDao =
            mock<SensorDao> { onBlocking { saveAll(sensorList) } doReturn 1 }
        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
        val expected = 1
        // Execute
        val result: Int = 1
        // Check
        verify(mockSensorDao).saveAll(sensorList)
        Assert.assertEquals(expected, result)
    }

    fun `Should save multiple Sensor Entities`() = testScope.runBlockingTest {
        // SETUP
        val sensor1 = SensorDataTestBuilder(1).build()
        val sensor2 = SensorDataTestBuilder(2).build()
        val sensor3 = SensorDataTestBuilder(3).build()
        val sensor4 = SensorDataTestBuilder(4).build()
        val sensorList = listOf(sensor1, sensor2, sensor3)
        val mockSensorDao = mock<SensorDao> {
            onBlocking { saveAll(sensorList) } doReturn 1
        }
        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
        val expected = 1
        val result: Int
        // Execute
        result = sensorLocalDataSource.saveSensors(sensorList)
        // Check
        verify(mockSensorDao).saveAll(sensorList)
        Assert.assertEquals(expected, result)


    }

    fun `Should save Sensor Entities with null values correctly`() = testScope.runBlockingTest {
        // SETUP
        val sensorList = listOf(
            SensorDataTestBuilder.withNullSensorStatusAndPadData(1).build(),
            SensorDataTestBuilder.withNullSensorStatusAndPadData(2).build(),
            SensorDataTestBuilder.withNullSensorStatusAndPadData(3).build()
        )
        val mockSensorDao = mock<SensorDao> {
            onBlocking {saveAll(sensorList)} doReturn 1
        }
        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
        val result: Int
        val expected = 1
        // EXECUTE
        result = sensorLocalDataSource.saveSensors(sensorList)
        // CHECK
        verify(mockSensorDao).saveAll(sensorList)
        Assert.assertEquals(expected, result)
    }
}
