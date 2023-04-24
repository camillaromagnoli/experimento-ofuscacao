package com.romagnolicamilla.data.remote.cast.source

import com.romagnolicamilla.data.base.BaseRemoteDataSource
import com.romagnolicamilla.data.remote.api.ProjectApi
import com.romagnolicamilla.data.utils.Endpoints
import com.romagnolicamilla.data.utils.QueryParams
import javax.inject.Inject

class CastDetailsRemoteDataSource @Inject constructor(
    private val projectApi: ProjectApi
) : BaseRemoteDataSource() {

    suspend fun getCastDetails(id: Long) = getResult {
        projectApi.get(
            url = Endpoints.PEOPLE.plus(id).plus(Endpoints.CAST_CREDITS),
            queries = mapOf(QueryParams.EMBED to QueryParams.SHOW)
        )
    }

}