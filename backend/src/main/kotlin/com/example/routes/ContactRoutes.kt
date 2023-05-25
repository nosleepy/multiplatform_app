package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

fun Route.rootRouting() {
    route("/") {
        get {
            call.respondText { "hello,world" }
        }
    }
}

fun Route.contactRouting() {
    route("/contact") {
        get {
            if (contactStorage.isNotEmpty()) {
                call.respondText {
                    json.encodeToString(ListSerializer(Contact.serializer()), contactStorage)
                }
            } else {
                call.respondText("No contacts found", status = HttpStatusCode.OK)
            }
        }
        post {
            val jsonStr = call.receive<String>()
            val contact = json.decodeFromString<Contact>(jsonStr).apply { this.id = ++flag }
            contactStorage.add(contactStorage.size, contact)
            call.respondText("Contact stored correctly", status = HttpStatusCode.OK)
        }
        delete("/{id}") {
            val id = call.parameters["id"]!!
            if (contactStorage.removeIf { it.id == id.toLong() }) {
                call.respondText("Contact removed correctly", status = HttpStatusCode.OK)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
        put {
            val jsonStr = call.receive<String>()
            val contact = json.decodeFromString<Contact>(jsonStr)
            contactStorage.forEach {
                if (it.id == contact.id) {
                    it.name = contact.name
                    it.number = contact.number
                    it.email = contact.email
                    it.address = contact.address
                }
            }
            call.respondText("Contact update correctly", status = HttpStatusCode.OK)
        }
    }
}