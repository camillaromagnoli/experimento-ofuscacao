package com.romagnolicamilla.presentation.viewmodels.home

import androidx.lifecycle.LiveData
import com.romagnolicamilla.domain.show.entity.Show
import com.romagnolicamilla.domain.show.repository.ShowRepository
import com.romagnolicamilla.presentation.base.BaseViewModel
import com.romagnolicamilla.presentation.base.SingleLiveEvent
import com.romagnolicamilla.presentation.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ShowRepository,
) : BaseViewModel() {
    private val _showsLiveEvent = SingleLiveEvent<ViewState<List<Show>?>>()
    fun getShows(): LiveData<ViewState<List<Show>?>> = _showsLiveEvent

    private var pageNumber = 0

    val shows = mutableListOf<Show>()

    suspend fun loadShows() {
        if (pageNumber >= 0) {
            handleWork({ repository.getShows(pageNumber) }, _showsLiveEvent)
        }
    }

    fun handleLoadShowsSuccess(data: List<Show>?) {
        val filtered = data?.filterNot { shows.contains(it) }.orEmpty()
        shows.addAll(filtered)
        ++pageNumber
    }

    fun handleEndOfPages() {
        pageNumber = -1
    }
}