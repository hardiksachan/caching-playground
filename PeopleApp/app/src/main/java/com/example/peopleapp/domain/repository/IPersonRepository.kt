package com.example.peopleapp.domain.repository

import com.example.peopleapp.common.Resource
import com.example.peopleapp.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface IPersonRepository {
    fun getPeople(): Flow<Resource<List<Person>>>
}