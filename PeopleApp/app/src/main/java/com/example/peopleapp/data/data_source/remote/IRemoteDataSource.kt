package com.example.peopleapp.data.data_source.remote

import com.example.peopleapp.common.Resource
import com.example.peopleapp.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    fun getPeople(): Flow<Resource<List<Person>>>
}