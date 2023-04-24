package com.romagnolicamilla.data.remote.show.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.domain.show.entity.Image

data class ImageResponse(
    val medium: String?,
    val imdb: String?,
    val original: String?
) : BaseResponse<Image> {
    override fun toDomain() = Image(medium, imdb, original)
}