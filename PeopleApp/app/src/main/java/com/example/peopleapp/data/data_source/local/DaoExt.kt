package com.example.peopleapp.data.data_source.local

import com.example.peopleapp.data.datasource.local.CachedPerson
import com.example.peopleapp.domain.models.Person

private fun CachedPerson.toDomain() = Person(
    name, city, email
)

fun List<CachedPerson>.toDomain(): List<Person> = this.map { it.toDomain() }