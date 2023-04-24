package com.romagnolicamilla.data.remote.cast.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.data.remote.show.response.ShowResponse
import com.romagnolicamilla.domain.cast.entity.Embedded

data class EmbeddedResponse(
    val show: ShowResponse?,
) : BaseResponse<Embedded> {
    override fun toDomain() =
        Embedded(show?.toDomain())
}
