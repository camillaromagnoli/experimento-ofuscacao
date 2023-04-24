package com.romagnolicamilla.core.modules

import com.romagnolicamilla.core.base.ClientBuilder
import com.romagnolicamilla.data.remote.api.ProjectApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApi(gson: Gson): ProjectApi {
        return ClientBuilder.createService(ProjectApi::class.java, gson)
    }

}