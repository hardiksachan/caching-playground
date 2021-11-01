package com.example.peopleapp.data.data_source.remote

import com.example.peopleapp.common.Resource
import com.example.peopleapp.domain.models.Person

interface IRemoteDataSource {
    suspend fun getPeople(): Resource<List<Person>>
}