package com.example

import com.example.models.Person
import io.github.serpro69.kfaker.Faker
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

val faker = Faker()

fun Route.peopleRouting() {
    route("/people") {
        get {
            call.respond(status = HttpStatusCode.OK, message= getPeople())
        }
    }
}

fun Application.registerPeopleRoutes() {
    routing {
        peopleRouting()
    }
}

fun getPeople(size: Int = 20): List<Person> =
    (1..size).map { getRandomPerson() }


fun getRandomPerson() = Person(
    name = faker.name.name(),
    email = faker.internet.email(),
    city = faker.address.cityWithState()
)