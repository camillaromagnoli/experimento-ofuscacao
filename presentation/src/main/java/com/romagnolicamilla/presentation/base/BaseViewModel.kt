package com.romagnolicamilla.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romagnolicamilla.presentation.extensions.postError
import com.romagnolicamilla.presentation.extensions.postLoading
import com.romagnolicamilla.presentation.extensions.postSuccess
import com.romagnolicamilla.presentation.state.ViewState
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    suspend fun <T> handleWork(
        work: suspend () -> T?,
        stateLiveData: MutableLiveData<ViewState<T?>>
    ) {
        stateLiveData.postLoading()
        viewModelScope.launch {
            val result =
                runCatching { work() }
            result.onSuccess { response ->
                stateLiveData.postSuccess(response)
            }.onFailure { throwable ->
                stateLiveData.postError(throwable)
            }
        }
    }
}