package com.romagnolicamilla.data.remote.cast.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.data.remote.show.response.ImageResponse
import com.romagnolicamilla.domain.cast.entity.Person

data class PersonResponse(
    val id: Long,
    val name: String?,
    val image: ImageResponse?
) : BaseResponse<Person> {
    override fun toDomain() = Person(id, name, image?.toDomain())
}
