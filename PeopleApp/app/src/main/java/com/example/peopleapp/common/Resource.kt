package com.example.peopleapp.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Failure(val throwable: Throwable) : Resource<Nothing>()
}
