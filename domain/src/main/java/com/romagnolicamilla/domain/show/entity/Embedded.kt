package com.romagnolicamilla.domain.show.entity

import com.romagnolicamilla.domain.cast.entity.Cast

data class Embedded(val episodes: List<Episode>?, val cast: List<Cast>?)