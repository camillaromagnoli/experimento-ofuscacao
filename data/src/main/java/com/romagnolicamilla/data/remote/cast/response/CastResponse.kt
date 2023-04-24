package com.romagnolicamilla.data.remote.cast.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.domain.cast.entity.Cast

data class CastResponse(
    val person: PersonResponse?,
    val character: PersonResponse?
) : BaseResponse<Cast> {
    override fun toDomain() = Cast(
        person?.toDomain(),
        character?.toDomain()
    )
}