package com.example.daggerhilt.repository

import com.example.daggerhilt.model.BLog
import com.example.daggerhilt.network.NetworkMapper
import com.example.daggerhilt.network.Retrofit
import com.example.daggerhilt.room.BlogDao
import com.example.daggerhilt.room.CacheMapper
import com.example.daggerhilt.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val retrofit: Retrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlogs(): Flow<DataState<List<BLog>>> = flow {
        emit(DataState.Loading)
        try {
            val networkBlogs=retrofit.getblogs()
            val blogs=networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.getblogs()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}