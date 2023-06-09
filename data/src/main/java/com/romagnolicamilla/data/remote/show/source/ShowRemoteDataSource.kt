package com.romagnolicamilla.data.remote.show.source

import com.romagnolicamilla.data.base.BaseRemoteDataSource
import com.romagnolicamilla.data.remote.api.ProjectApi
import com.romagnolicamilla.data.utils.Endpoints
import com.romagnolicamilla.data.utils.QueryParams
import javax.inject.Inject

class ShowRemoteDataSource @Inject constructor(
    private val projectApi: ProjectApi
) : BaseRemoteDataSource() {
    suspend fun getShows(pageNumber: Int) = getResult {
        projectApi.get(
            url = Endpoints.SHOWS,
            queries = mapOf(QueryParams.PAGE_NUMBER to pageNumber)
        )
    }

    suspend fun searchShows(queryString: String) = getResult {
        projectApi.get(
            url = Endpoints.SEARCH_SHOWS,
            queries = mapOf(QueryParams.QUERY_SEARCH to queryString)
        )
    }

    suspend fun searchPeople(queryString: String) = getResult {
        projectApi.get(
            url = Endpoints.SEARCH_PEOPLE,
            queries = mapOf(QueryParams.QUERY_SEARCH to queryString)
        )
    }

    suspend fun getDetails(id: Long) = getResult {
        projectApi.get(
            url = Endpoints.SHOW_DETAILS.plus(id),
            queries = mapOf(
                QueryParams.EMBED.plus("[0]") to QueryParams.CAST,
                QueryParams.EMBED.plus("[1]") to QueryParams.EPISODES
            )
        )
    }
}