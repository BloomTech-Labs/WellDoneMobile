package com.versilistyson.welldone.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.sensor.SensorRemoteDataSource
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
    fun `Should fetch sensors`() {

    }

    @Test
    fun `Should fetch sensors from local database`() {

    }

    @Test
    fun `Should force fetch sensors from api`() {

    }

}