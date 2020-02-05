package com.versilistyson.welldone.domain.framework.usecases.log

import com.versilistyson.welldone.data.api.log.LogImageApi
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import okhttp3.MultipartBody
import javax.inject.Inject

class AddImageToLogUseCase @Inject constructor(private val logImageRepository: LogImageRepository): UseCase<Entity.LogImage, AddImageToLogUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Entity.LogImage> {
//        try {
//            val imageResult =
//                logImageRepository.addImageDetailsToLog(params.file, LogImageApi.Dto.LogImageMeta(caption = params.caption, logId = params.logId))
//        } catch(e: Exception){
//
//        }
        TODO()
    }

    data class Params(val file: MultipartBody.Part, val logId: Long, val caption: String)
}