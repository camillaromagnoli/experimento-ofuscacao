package com.romagnolicamilla.data.remote.cast.repository

import com.romagnolicamilla.data.remote.cast.response.CastDetailsResponse
import com.romagnolicamilla.data.remote.cast.source.CastDetailsRemoteDataSource
import com.romagnolicamilla.data.utils.getList
import com.romagnolicamilla.domain.cast.entity.CastDetails
import com.romagnolicamilla.domain.cast.repository.CastDetailsRepository
import javax.inject.Inject

class CastDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: CastDetailsRemoteDataSource
) : CastDetailsRepository {

    override suspend fun getCastDetails(id: Long): List<CastDetails>? =
        getList<CastDetails, CastDetailsResponse> {
            remoteDataSource.getCastDetails(id)
        }
}