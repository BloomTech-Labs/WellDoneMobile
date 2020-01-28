package com.versilistyson.welldone.data.datasource.sensor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.db.StoreKey
import com.versilistyson.welldone.data.db.sensor.SensorDao
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.data.util.RepositoryConstants.Companion.SENSORS_KEY
import com.versilistyson.welldone.test_util.builder.sensor.SensorDataTestBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.amshove.kluent.shouldBeEqualTo
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

    private val mockSensorDao: SensorDao = mock()

    @Test
    fun `Should call saveAll from SensorDao`() = testScope.runBlockingTest {
        // Setup
        val sensorList = listOf(SensorDataTestBuilder.withDefaults().build())
        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
        // Execute
        sensorLocalDataSource.saveSensors(SENSORS_KEY, sensorList)
        // Check
        verify(mockSensorDao).saveAll(StoreKey.SensorsKey(), sensorList)
    }

    @Test
    fun `Should call getAll from SensorDao`() = testScope.runBlockingTest {
        // SETUP
        val sensorList = listOf(
            SensorDataTestBuilder(sensorId = 1).build(),
            SensorDataTestBuilder(sensorId=2).build(),
            SensorDataTestBuilder(sensorId = 3).build()
        )

        val sensorLocalDataSource = SensorLocalDataSourceImpl(mockSensorDao)
        val result: Flow<List<SensorData>>
        val expected = flowOf(sensorList)

        // EXECUTE
        whenever(mockSensorDao.getAll(StoreKey.SensorsKey())).thenReturn(expected)
        result = sensorLocalDataSource.getSensors(SENSORS_KEY)
        // CHECK
        verify(mockSensorDao).getAll(StoreKey.SensorsKey())
        expected shouldBeEqualTo result
    }
}
