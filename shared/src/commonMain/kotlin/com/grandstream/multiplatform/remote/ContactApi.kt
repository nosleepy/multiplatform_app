package com.grandstream.multiplatform.remote

import com.grandstream.multiplatform.entity.Contact
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
class ContactApi(
    private val client: HttpClient,
    private var baseUrl: String = "http://172.16.31.76:8888",
): KoinComponent {
    private val json: Json by lazy { get() }

    suspend fun getContactList(): List<Contact> {
        val jsonStr = client.get("$baseUrl/contact").body<String>()
        return json.decodeFromString(ListSerializer(Contact.serializer()), jsonStr)
    }

    suspend fun addContact(contact: Contact): HttpResponse {
        return client.post("$baseUrl/contact") {
            contentType(ContentType.Application.Json)
            setBody(contact)
        }
    }

    suspend fun deleteContact(id: Long): HttpResponse {
        return client.delete("$baseUrl/contact/$id")
    }

    suspend fun updateContact(contact: Contact): HttpResponse {
        return client.put("$baseUrl/contact") {
            contentType(ContentType.Application.Json)
            setBody(contact)
        }
    }
}