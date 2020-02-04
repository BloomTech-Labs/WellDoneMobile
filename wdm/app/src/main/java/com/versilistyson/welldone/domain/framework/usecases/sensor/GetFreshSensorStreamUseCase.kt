package com.versilistyson.welldone.domain.framework.usecases.sensor

import com.versilistyson.welldone.domain.common.ResponseResult
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.SensorRepository
import com.versilistyson.welldone.domain.framework.usecases.common.FlowUseCase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class GetFreshSensorStreamUseCase(
    private val sensorRepository: SensorRepository
) : FlowUseCase<ResponseResult<Entity.Sensors>, FlowUseCase.None>() {

    override suspend fun run(params: None){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}