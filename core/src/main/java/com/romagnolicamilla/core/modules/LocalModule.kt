package com.romagnolicamilla.core.modules

import android.content.Context
import androidx.room.Room
import com.romagnolicamilla.data.encrypt.EncryptSharedPreferencesImpl
import com.romagnolicamilla.data.remote.show.local.ShowLocalDataSource
import com.romagnolicamilla.data.remote.show.local.ShowLocalDataSourceImpl
import com.romagnolicamilla.data.remote.show.local.database.ShowDatabase
import com.romagnolicamilla.domain.pin.EncryptSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideMovieLocalDataSource(database: ShowDatabase): ShowLocalDataSource {
        return ShowLocalDataSourceImpl(database)
    }

    @Provides
    fun providesAppDb(@ApplicationContext context: Context): ShowDatabase {
        return Room.databaseBuilder(
            context,
            ShowDatabase::class.java, "database-shows"
        ).build()
    }

    @Singleton
    @Provides
    fun providesEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptSharedPreferences {
        return EncryptSharedPreferencesImpl(context)
    }
}