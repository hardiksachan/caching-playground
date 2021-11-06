package com.example.peopleapp.common

import kotlinx.coroutines.flow.*

inline fun <CacheModel, NetworkModel, ReturnType> networkBoundResource(
    crossinline loadFromNetwork: suspend () -> Resource<NetworkModel>,
    crossinline loadFromCache: () -> Flow<Resource<CacheModel>>,
    crossinline shouldRefreshCache: () -> Boolean,
    crossinline saveToCache: suspend (NetworkModel) -> Unit,
    crossinline mapCacheModel: (CacheModel) -> ReturnType,
    crossinline mapNetworkModel: (NetworkModel) -> ReturnType
): Flow<Resource<ReturnType>> = flow {
    emitAll(loadFromCache().map {
        it.map { c ->
            mapCacheModel(c)
        }
    })
    
    if (shouldRefreshCache()) {
        try {
            when (val networkResponse = loadFromNetwork()) {
                is Resource.Failure -> emit(networkResponse)
                is Resource.Success -> saveToCache(networkResponse.data)
            }
        } catch (th: Throwable) {
            emit(Resource.Failure(th))
        }
    }
}