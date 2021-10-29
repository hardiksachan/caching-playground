package com.example.peopleapp.common

import kotlin.coroutines.CoroutineContext

interface IDispatcherProvider {
    val UI: CoroutineContext
    val IO: CoroutineContext
}