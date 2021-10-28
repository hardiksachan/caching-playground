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
            val size: Int = try {
                call.request.queryParameters["size"]?.toInt() ?: 20
            }
            catch (e: NumberFormatException) {
                return@get call.respondText("Not a valid size", status = HttpStatusCode.BadRequest)
            }

            call.respond(status = HttpStatusCode.OK, message= getPeople(size))
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