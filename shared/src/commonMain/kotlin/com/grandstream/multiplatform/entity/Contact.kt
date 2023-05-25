package com.grandstream.multiplatform.entity

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
@Serializable
data class Contact(
    val id: Long,
    var name: String,
    var number: String,
    var email: String,
    var address: String
)