package com.example.daggerhilt.di

import com.example.daggerhilt.network.NetworkMapper
import com.example.daggerhilt.network.Retrofit
import com.example.daggerhilt.repository.MainRepository
import com.example.daggerhilt.room.BlogDao
import com.example.daggerhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(blogDao: BlogDao,
                              retrofit: Retrofit,
                              cacheMapper: CacheMapper,
                              networkMapper: NetworkMapper):MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}