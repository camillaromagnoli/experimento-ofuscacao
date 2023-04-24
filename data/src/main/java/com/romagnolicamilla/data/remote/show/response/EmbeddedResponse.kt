package com.romagnolicamilla.data.remote.show.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.data.remote.cast.response.CastResponse
import com.romagnolicamilla.domain.show.entity.Embedded

data class EmbeddedResponse(
    val episodes: List<EpisodeResponse>?,
    val cast: List<CastResponse>?,
) : BaseResponse<Embedded> {
    override fun toDomain() =
        Embedded(episodes?.map { it.toDomain() }, cast?.map { it.toDomain() })
}
