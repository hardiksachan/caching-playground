package com.example.peopleapp.data.data_source.local

import com.example.peopleapp.common.Resource
import com.example.peopleapp.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface ILocalCache {
    suspend fun refreshCache(people: List<Person>)

    suspend fun clearCache()

    fun readCache(): Flow<Resource<List<Person>>>
}