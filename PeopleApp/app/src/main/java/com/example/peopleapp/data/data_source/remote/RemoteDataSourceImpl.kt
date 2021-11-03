package com.example.peopleapp.data.data_source.remote

import com.example.peopleapp.common.IDispatcherProvider
import com.example.peopleapp.common.Resource
import com.example.peopleapp.data.data_source.remote.dto.PersonDto
import com.example.peopleapp.data.data_source.remote.dto.toDomain
import com.example.peopleapp.domain.models.Person
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl(
    private val endpoint: String,
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

    override fun getPeople(): Flow<Resource<List<Person>>> = flow {
        try {
            emit(
                Resource.Success(
                    data = client.get<List<PersonDto>> {
                        url(endpoint)
                    }.toDomain(),
                    isCached = false
                )
            )
        } catch (th: Throwable) {
            emit(Resource.Failure(th))
        }
    }.flowOn(dispatcherProvider.IO)
}