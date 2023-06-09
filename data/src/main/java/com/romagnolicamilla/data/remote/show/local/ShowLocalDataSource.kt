package com.romagnolicamilla.data.remote.show.local

import com.romagnolicamilla.data.remote.show.local.database.ShowEntity

interface ShowLocalDataSource {

    suspend fun insertFavorite(show: ShowEntity)

    suspend fun removeFavorite(show: ShowEntity)

    suspend fun retrieveFavorite(id: Long?): ShowEntity?

    suspend fun retrieveFavorites(): List<ShowEntity>?

}