package com.versilistyson.welldone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.welldone.domain.common.Either
import com.versilistyson.welldone.domain.framework.entity.Entity
import com.versilistyson.welldone.domain.framework.usecases.log.GetLogImagesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogDialogViewModel @Inject constructor(private val getLogImagesUseCase: GetLogImagesUseCase): ViewModel() {

    private val _imageLink = MutableLiveData<Entity.LogImage>()
    val imageLink: LiveData<Entity.LogImage>
        get() = _imageLink

    fun getLogImagesForLogId(logId: Long){
        viewModelScope.launch {
            val value = getLogImagesUseCase.invoke(viewModelScope, GetLogImagesUseCase.Params(logId))
            if((value as Either.Right).right.isNotEmpty()) {
                _imageLink.postValue(
                    value.right[0]
                )
            }
        }
    }
}