package com.romagnolicamilla.data.remote.cast.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.domain.cast.entity.CastDetails
import com.google.gson.annotations.SerializedName

class CastDetailsResponse(
    @SerializedName("_embedded")
    private val embedded: EmbeddedResponse?,
) : BaseResponse<CastDetails> {
    override fun toDomain() = CastDetails(
        embedded?.toDomain()
    )
}