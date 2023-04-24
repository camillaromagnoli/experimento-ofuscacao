package com.romagnolicamilla.domain.cast.repository

import com.romagnolicamilla.domain.cast.entity.CastDetails

interface CastDetailsRepository {
    suspend fun getCastDetails(id: Long): List<CastDetails>?
}