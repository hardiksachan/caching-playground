package com.example.peopleapp.common

import android.util.Log
import kotlinx.coroutines.flow.*


inline fun <CacheModel, NetworkModel, ReturnType> networkBoundResource(
    crossinline loadFromNetwork: suspend () -> Resource<NetworkModel>,
    crossinline loadFromCache: () -> Flow<Resource<CacheModel>>,
    crossinline shouldRefreshCache: () -> Boolean,
    crossinline saveToCache: suspend (NetworkModel) -> Unit,
    crossinline mapCacheModel: (CacheModel) -> ReturnType,
): Flow<Resource<ReturnType>> = flow {
    val TAG = "NetworkBoundResource"

    emit(loadFromCache().first().map { mapCacheModel(it) })

    if (shouldRefreshCache()) {
        Log.i(TAG, "refreshing cache")
        try {
            when (val networkResponse = loadFromNetwork()) {
                is Resource.Failure -> emit(networkResponse)
                is Resource.Success -> saveToCache(networkResponse.data)
            }
            Log.i(TAG, "cache refreshed")
        } catch (th: Throwable) {
            emit(Resource.Failure(th))
            Log.i(TAG, "cache refresh failed. ${th.stackTrace}")
        }
    }

    emitAll(loadFromCache().map {
        Log.i(TAG, "emitting From Cache: $it")
        it.map { c ->
            mapCacheModel(c)
        }
    })
}