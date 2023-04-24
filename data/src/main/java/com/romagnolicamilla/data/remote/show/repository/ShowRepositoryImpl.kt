package com.romagnolicamilla.data.remote.show.repository

import com.romagnolicamilla.data.remote.show.local.ShowLocalDataSource
import com.romagnolicamilla.data.remote.show.local.database.ShowEntity
import com.romagnolicamilla.data.remote.show.response.SearchResponse
import com.romagnolicamilla.data.remote.show.response.ShowResponse
import com.romagnolicamilla.data.remote.show.source.ShowRemoteDataSource
import com.romagnolicamilla.data.utils.getList
import com.romagnolicamilla.data.utils.getObject
import com.romagnolicamilla.domain.show.entity.Search
import com.romagnolicamilla.domain.show.entity.Show
import com.romagnolicamilla.domain.show.repository.ShowRepository
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: ShowRemoteDataSource,
    private val localDataSource: ShowLocalDataSource
) : ShowRepository {
    override suspend fun getShows(pageNumber: Int): List<Show>? =
        getList<Show, ShowResponse> { remoteDataSource.getShows(pageNumber) }

    override suspend fun searchShows(queryString: String): List<Search>? =
        getList<Search, SearchResponse> { remoteDataSource.searchShows(queryString) }

    override suspend fun searchPeople(queryString: String): List<Search>? =
        getList<Search, SearchResponse> { remoteDataSource.searchPeople(queryString) }

    override suspend fun getDetails(id: Long) =
        getObject<Show, ShowResponse> { remoteDataSource.getDetails(id) }

    override suspend fun retrieveFavorites(): List<Show>? =
        localDataSource.retrieveFavorites()?.map { it.toDomain() }

    override suspend fun retrieveFavorite(id: Long?): Show? =
        localDataSource.retrieveFavorite(id)?.toDomain()

    override suspend fun removeFavorite(show: Show) =
        localDataSource.removeFavorite(ShowEntity.toEntity(show))

    override suspend fun insertFavorite(show: Show) =
        localDataSource.insertFavorite(ShowEntity.toEntity(show))

}