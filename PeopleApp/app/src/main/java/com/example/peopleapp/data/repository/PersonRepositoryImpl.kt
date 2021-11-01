package com.example.peopleapp.data.repository

import com.example.peopleapp.common.Resource
import com.example.peopleapp.data.data_source.remote.IRemoteDataSource
import com.example.peopleapp.domain.models.Person
import com.example.peopleapp.domain.repository.IPersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val remoteDataSource: IRemoteDataSource
) : IPersonRepository {

    override fun getPeople(): Flow<Resource<List<Person>>> = remoteDataSource.getPeople()
}
