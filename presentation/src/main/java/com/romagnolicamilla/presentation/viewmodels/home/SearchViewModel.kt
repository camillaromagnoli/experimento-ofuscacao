package com.romagnolicamilla.presentation.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romagnolicamilla.domain.show.entity.Search
import com.romagnolicamilla.domain.show.repository.ShowRepository
import com.romagnolicamilla.presentation.base.BaseViewModel
import com.romagnolicamilla.presentation.base.SingleLiveEvent
import com.romagnolicamilla.presentation.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ShowRepository
) : BaseViewModel() {

    private val _searchLiveEvent = SingleLiveEvent<ViewState<List<Search>?>>()
    fun getSearchResult(): LiveData<ViewState<List<Search>?>> = _searchLiveEvent

    private val queryLiveData = MutableLiveData<String>()

    suspend fun searchShows() {
        makeSearch { repository.searchShows(it) }
    }

    suspend fun searchPeople() {
        makeSearch { repository.searchPeople(it) }
    }

    private suspend fun makeSearch(call: suspend (String) -> List<Search>?) {
        queryLiveData.value.takeUnless { it.isNullOrEmpty() }?.run {
            handleWork({ call(this) }, _searchLiveEvent)
        }
    }

    fun handleQueryChange(query: String) {
        queryLiveData.postValue(query)
    }

}