package com.versilistyson.welldone.domain.use_cases

import androidx.lifecycle.LiveData
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.versilistyson.welldone.data.db.sensor.SensorData
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import com.versilistyson.welldone.domain.framework.usecases.GetSensorsUseCase
import com.versilistyson.welldone.domain.framework.usecases.UseCase
import com.versilistyson.welldone.test_util.BaseCoroutineTest
import com.versilistyson.welldone.test_util.builder.sensor.SensorDataTestBuilder
import com.versilistyson.welldone.test_util.builder.sensor.SensorEntityTestBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class GetSensorsUseCaseTest: BaseCoroutineTest() {

    private val mockSensorRepository: SensorRepository = mock()

    private val sensorDataList = listOf(
        SensorDataTestBuilder(sensorId = 1).build(),
        SensorDataTestBuilder(sensorId = 2).build(),
        SensorDataTestBuilder(sensorId = 3).build()
    )

    private val sensorEntityList = listOf(
        SensorEntityTestBuilder(sensorId = 1).build(),
        SensorEntityTestBuilder(sensorId = 2, location = LatLng(2.0, 2.0)).build()
    )

    private val sensorsEntity = Entity.Sensors(
        sensorEntityList, LatLng(1.5, 1.5)
    )

    @Test
    fun `Run should return an either for loading, data and error`() {
//        runBlocking {
//            try {
//                val sensorUseCase = GetSensorsUseCase(mockSensorRepository, testScope)
//                val mockStoreResponse: StoreResponse<List<SensorData>> = mock()
//
//                whenever(mockStoreResponse.requireData()).thenReturn(sensorDataList)
//                whenever(mockStoreResponse.origin).thenReturn(ResponseOrigin.Fetcher)
//                whenever(mockSensorRepository.fetchFreshSensors()).thenReturn(
//                    flowOf(
//                        mockStoreResponse
//                    )
//                )
//
//                val eitherFn: (Either<Failure, LiveData<Entity.Sensors>>) -> Unit = {
//                    if (it is Either.Right) {
//                        it.right shouldBeEqualTo sensorsEntity
//                        this.cancel()
//                    } else if (it is Either.Left) {
//                        it.left shouldBeEqualTo Failure.CurrentlyLoading
//                    }
//                }
//                sensorUseCase.invoke(testScope, UseCase.None(), eitherFn)
//            } catch(e: CancellationException){
//
//            }
//        }
    }

}