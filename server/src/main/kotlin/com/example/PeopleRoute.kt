package com.example

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.peopleRouting() {
    route("/people") {
        get {
            call.respondText("Not implemented", status = HttpStatusCode.InternalServerError)
        }
    }
}

fun Application.registerPeopleRoutes() {
    routing {
        peopleRouting()
    }
}