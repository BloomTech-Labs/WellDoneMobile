package com.versilistyson.welldone.data.datasource.sensor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
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
    fun hi(){

    }

//    @Test
//    fun `Should send back a single sensor entity`() = testScope.runBlockingTest {
//        // Setup
//        val sensor = SensorDataTestBuilder.withDefaults().build()
//        val sensorList = listOf(sensor)
//        val mockSensorDao =
//            mock<SensorDao> { onBlocking { saveAll(sensorList) } doReturn 1 }
//        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
//        val expected = 1
//        // Execute
//        val result: Int = 1
//        // Check
//        verify(mockSensorDao).saveAll(sensorList)
//        Assert.assertEquals(expected, result)
//    }
//
//    fun `Should save multiple Sensor Entities`() = testScope.runBlockingTest {
//        // SETUP
//        val sensor1 = SensorDataTestBuilder(1).build()
//        val sensor2 = SensorDataTestBuilder(2).build()
//        val sensor3 = SensorDataTestBuilder(3).build()
//        val sensor4 = SensorDataTestBuilder(4).build()
//        val sensorList = listOf(sensor1, sensor2, sensor3)
//        val mockSensorDao = mock<SensorDao> {
//            onBlocking { saveAll(sensorList) } doReturn 1
//        }
//        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
//        val expected = 1
//        val result: Int
//        // Execute
//        result = sensorLocalDataSource.saveSensors(sensorList)
//        // Check
//        verify(mockSensorDao).saveAll(sensorList)
//        Assert.assertEquals(expected, result)
//
//
//    }
//
//    fun `Should save Sensor Entities with null values correctly`() = testScope.runBlockingTest {
//        // SETUP
//        val sensorList = listOf(
//            SensorDataTestBuilder.withNullSensorStatusAndPadData(1).build(),
//            SensorDataTestBuilder.withNullSensorStatusAndPadData(2).build(),
//            SensorDataTestBuilder.withNullSensorStatusAndPadData(3).build()
//        )
//        val mockSensorDao = mock<SensorDao> {
//            onBlocking {saveAll(sensorList)} doReturn 1
//        }
//        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
//        val result: Int
//        val expected = 1
//        // EXECUTE
//        result = sensorLocalDataSource.saveSensors(sensorList)
//        // CHECK
//        verify(mockSensorDao).saveAll(sensorList)
//        Assert.assertEquals(expected, result)
//    }
}
