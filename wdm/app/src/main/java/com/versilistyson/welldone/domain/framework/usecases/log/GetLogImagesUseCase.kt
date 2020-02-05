package com.versilistyson.welldone.domain.framework.usecases.log

import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.common.Failure
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.repository.LogImageRepository
import com.versilistyson.welldone.domain.framework.usecases.common.UseCase
import javax.inject.Inject

class GetLogImagesUseCase @Inject constructor(private val logImageRepository: LogImageRepository): UseCase<List<Entity.LogImage>, GetLogImagesUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Entity.LogImage>> {
        return try{
            val images = logImageRepository.refresh(params.logId)
            val mappedImages = mutableListOf<Entity.LogImage>()
            images.forEach {
                mappedImages.add(it.map())
            }
            Either.Right(mappedImages)
        } catch(e: Exception){
            Either.Left(GetLogImagesFailure(e))
        }
    }

    data class GetLogImagesFailure(val e: Exception = Exception()): Failure.FeatureFailure(e)

    data class Params(val logId: Long)
}