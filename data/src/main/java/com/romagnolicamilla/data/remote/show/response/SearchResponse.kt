package com.romagnolicamilla.data.remote.show.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.data.remote.cast.response.PersonResponse
import com.romagnolicamilla.domain.show.entity.Search

data class SearchResponse(
    val show: ShowResponse?,
    val score: Double?,
    val person: PersonResponse?
) : BaseResponse<Search> {
    override fun toDomain() = Search(show?.toDomain(), person?.toDomain(), score)
}

