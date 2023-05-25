package com.grandstream.multiplatform.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.util.DatabaseUtil
import com.grandstream.multiplatform.util.HttpUtil
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
class RemoteViewModel: ViewModel() {
    var name by mutableStateOf("hello")
    var remoteList by mutableStateOf(listOf<Contact>())

    init {
        viewModelScope.launch {
            remoteList = HttpUtil.getContactList()
        }
    }

    fun onRemoteDelete(id: Long) {
        viewModelScope.launch {
            val res = HttpUtil.deleteContact(id)
            if (res.status == HttpStatusCode.OK) {
                remoteList = HttpUtil.getContactList()
            }
        }
    }

    fun onRemoteUpdate(contact: Contact) {
        viewModelScope.launch {
            val res = HttpUtil.updateContact(contact)
            if (res.status == HttpStatusCode.OK) {
                remoteList = HttpUtil.getContactList()
            }
        }
    }

    fun onRemoteAdd(contact: Contact) {
        viewModelScope.launch {
            val res = HttpUtil.addContact(contact)
            if (res.status == HttpStatusCode.OK) {
                remoteList = HttpUtil.getContactList()
            }
        }
    }

    fun onLocalAdd(contact: Contact) {
        viewModelScope.launch {
            DatabaseUtil.dbQuery.insertContact(contact.name, contact.number, contact.email, contact.address)
        }
    }
}