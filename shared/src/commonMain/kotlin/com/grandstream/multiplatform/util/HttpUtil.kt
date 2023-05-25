package com.grandstream.multiplatform.util

import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
object HttpUtil {
    private val httpClient = httpClient()

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    suspend fun getContactList(): List<Contact> {
        val jsonStr = httpClient.get("http://172.16.31.76:8888/contact").body<String>()
        return json.decodeFromString(ListSerializer(Contact.serializer()), jsonStr)
    }

    suspend fun addContact(contact: Contact): HttpResponse {
        return httpClient.post("http://172.16.31.76:8888/contact") {
            contentType(ContentType.Application.Json)
            setBody(contact)
        }
    }

    suspend fun deleteContact(id: Long): HttpResponse {
        return httpClient.delete("http://172.16.31.76:8888/contact/$id")
    }

    suspend fun updateContact(contact: Contact): HttpResponse {
        return httpClient.put("http://172.16.31.76:8888/contact") {
            contentType(ContentType.Application.Json)
            setBody(contact)
        }
    }
}