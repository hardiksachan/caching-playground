package com.example.peopleapp.common

sealed class Resource<out T> {
    data class Success<out T>(
        val data: T,
        val isCached: Boolean
    ) : Resource<T>()
    data class Failure(val throwable: Throwable) : Resource<Nothing>()
}
