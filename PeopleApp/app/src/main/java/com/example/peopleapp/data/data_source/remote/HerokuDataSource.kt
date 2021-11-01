package com.example.peopleapp.data.data_source.remote

import com.example.peopleapp.common.IDispatcherProvider
import com.example.peopleapp.common.Resource
import com.example.peopleapp.domain.models.Person
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

class HerokuDataSource(
    private val baseUrl: String,
    private val dispatcherProvider: IDispatcherProvider
) : IRemoteDataSource {

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }

        engine {
            connectTimeout = 60_000
            socketTimeout = 60_000
        }
    }

    override suspend fun getPeople(): Resource<List<Person>> {
        TODO("Not yet implemented")
    }

}