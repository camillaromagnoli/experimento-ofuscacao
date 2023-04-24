package com.romagnolicamilla.core.modules

import com.romagnolicamilla.data.remote.api.ProjectApi
import com.romagnolicamilla.data.remote.cast.repository.CastDetailsRepositoryImpl
import com.romagnolicamilla.data.remote.cast.source.CastDetailsRemoteDataSource
import com.romagnolicamilla.data.remote.show.local.ShowLocalDataSource
import com.romagnolicamilla.data.remote.show.repository.ShowRepositoryImpl
import com.romagnolicamilla.data.remote.show.source.ShowRemoteDataSource
import com.romagnolicamilla.domain.cast.repository.CastDetailsRepository
import com.romagnolicamilla.domain.show.repository.ShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideShowRepository(
        remoteDataSource: ShowRemoteDataSource,
        localDataSource: ShowLocalDataSource
    ): ShowRepository {
        return ShowRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    fun provideShowRemoteDataSource(projectApi: ProjectApi): ShowRemoteDataSource {
        return ShowRemoteDataSource(projectApi)
    }

    @Provides
    @ViewModelScoped
    fun provideCastDetailsRepository(dataSource: CastDetailsRemoteDataSource): CastDetailsRepository {
        return CastDetailsRepositoryImpl(dataSource)
    }

    @Provides
    fun provideCastDetailsRemoteDataSource(projectApi: ProjectApi): CastDetailsRemoteDataSource {
        return CastDetailsRemoteDataSource(projectApi)
    }

}