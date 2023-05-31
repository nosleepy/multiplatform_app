package com.grandstream.multiplatform.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.remote.ContactApi
import com.grandstream.multiplatform.repository.IContactRepository
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
class RemoteViewModel: ViewModel(), KoinComponent {
    var name by mutableStateOf("hello")
    var remoteList by mutableStateOf(listOf<Contact>())
    private val contactApi: ContactApi by lazy { get() }
    private val contactRepository: IContactRepository by lazy { get() }

    init {
        viewModelScope.launch {
            remoteList = contactApi.getContactList()
        }
    }

    fun onRemoteDelete(id: Long) {
        viewModelScope.launch {
            val res = contactApi.deleteContact(id)
            if (res.status == HttpStatusCode.OK) {
                remoteList = contactApi.getContactList()
            }
        }
    }

    fun onRemoteUpdate(contact: Contact) {
        viewModelScope.launch {
            val res = contactApi.updateContact(contact)
            if (res.status == HttpStatusCode.OK) {
                remoteList = contactApi.getContactList()
            }
        }
    }

    fun onRemoteAdd(contact: Contact) {
        viewModelScope.launch {
            val res = contactApi.addContact(contact)
            if (res.status == HttpStatusCode.OK) {
                remoteList = contactApi.getContactList()
            }
        }
    }

    fun onLocalAdd(contact: Contact) {
        viewModelScope.launch {
            contactRepository.insertContact(contact)
        }
    }
}