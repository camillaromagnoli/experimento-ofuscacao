package com.romagnolicamilla.domain.show.entity

import com.romagnolicamilla.domain.cast.entity.Person

data class Search(val show: Show?, val person: Person?, val score: Double?) {
    fun getName() = show?.name ?: person?.name
    fun getImage() = show?.image?.getImageUrl() ?: person?.image?.getImageUrl()
}