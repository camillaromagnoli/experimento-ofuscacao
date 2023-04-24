package com.romagnolicamilla.data.base

interface BaseResponse<D> {
    fun toDomain(): D
}