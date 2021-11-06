package com.example.peopleapp.common

sealed class Resource<out T> {
    data class Success<out T>(
        val data: T
    ) : Resource<T>()
    data class Failure(val throwable: Throwable) : Resource<Nothing>()


    fun <V> map(f: (T) -> V): Resource<V> = when (this) {
        is Failure -> this
        is Success -> Resource.Success(f(this.data))
    }
}
