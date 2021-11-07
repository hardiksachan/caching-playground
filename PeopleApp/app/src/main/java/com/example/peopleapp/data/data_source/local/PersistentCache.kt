package com.example.peopleapp.data.data_source.local

import com.example.peopleapp.common.IDispatcherProvider
import com.example.peopleapp.common.Resource
import com.example.peopleapp.data.datasource.local.PeopleCacheQueries
import com.example.peopleapp.domain.models.Person
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class PersistentCache(
    private val peopleCacheQueries: PeopleCacheQueries,
    private val dispatcherProvider: IDispatcherProvider
) : ILocalCache {
    override suspend fun refreshCache(people: List<Person>) = people.forEach { person ->
        person.run {
            withContext(dispatcherProvider.IO) {
                peopleCacheQueries.refreshCache(
                    name, city, email
                )
            }
        }
    }

    override suspend fun clearCache() = withContext(dispatcherProvider.IO) {
        peopleCacheQueries
            .clearCache()
    }

    override fun readCache(): Flow<Resource<List<Person>>> = peopleCacheQueries
        .getPeople()
        .asFlow()
        .mapToList(dispatcherProvider.IO)
        .map { dao ->
            Resource.Success(
                dao.toDomain()
            )
        }
        .catch { th ->
            Resource.Failure(th)
        }
        .flowOn(dispatcherProvider.IO)

}


