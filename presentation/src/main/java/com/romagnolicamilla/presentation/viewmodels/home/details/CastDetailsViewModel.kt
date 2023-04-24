package com.romagnolicamilla.presentation.viewmodels.home.details

import androidx.lifecycle.LiveData
import com.romagnolicamilla.domain.cast.entity.CastDetails
import com.romagnolicamilla.domain.cast.repository.CastDetailsRepository
import com.romagnolicamilla.presentation.base.BaseViewModel
import com.romagnolicamilla.presentation.base.SingleLiveEvent
import com.romagnolicamilla.presentation.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastDetailsViewModel @Inject constructor(
    private val repository: CastDetailsRepository
) : BaseViewModel() {

    private val _detailsLiveEvent = SingleLiveEvent<ViewState<List<CastDetails>?>>()
    fun getCastDetails(): LiveData<ViewState<List<CastDetails>?>> = _detailsLiveEvent

    var id: Long = -1L

    suspend fun loadDetails() {
        takeUnless { id == -1L }?.run {
            handleWork({ repository.getCastDetails(id) }, _detailsLiveEvent)
        }
    }
}