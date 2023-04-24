package com.romagnolicamilla.data.remote.show.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.domain.show.entity.Episode

data class EpisodeResponse(
    val id: Long,
    val name: String?,
    val season: Long?,
    val number: Long?,
    val image: ImageResponse?,
    val summary: String?
) : BaseResponse<Episode> {
    override fun toDomain() = Episode(
        id,
        name,
        season,
        number,
        image?.toDomain(),
        summary
    )
}
