package com.example.peopleapp.data.data_source.remote.dto

import com.example.peopleapp.domain.models.Person
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val name: String,
    val city: String,
    val email: String
) {
    fun toDomain() = Person(name, city, email)
}

fun List<PersonDto>.toDomain() = this.map { it.toDomain() }