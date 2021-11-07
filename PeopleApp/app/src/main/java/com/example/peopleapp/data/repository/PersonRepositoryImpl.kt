package com.example.peopleapp.data.repository

import com.example.peopleapp.common.Resource
import com.example.peopleapp.common.networkBoundResource
import com.example.peopleapp.data.data_source.local.ILocalCache
import com.example.peopleapp.data.data_source.remote.IRemoteDataSource
import com.example.peopleapp.domain.models.Person
import com.example.peopleapp.domain.repository.IPersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val remoteDataSource: IRemoteDataSource,
    private val cache: ILocalCache
) : IPersonRepository {

    override fun getPeople(): Flow<Resource<List<Person>>> = networkBoundResource(
        loadFromNetwork = { remoteDataSource.getPeople() },
        loadFromCache = { cache.readCache() },
        shouldRefreshCache = { true },
        saveToCache = {
            cache.refreshCache(it)
        },
        mapCacheModel = { it }
    )
}
