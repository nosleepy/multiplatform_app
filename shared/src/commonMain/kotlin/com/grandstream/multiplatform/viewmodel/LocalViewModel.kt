package com.grandstream.multiplatform.viewmodel

import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.repository.IContactRepository
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.js.ExperimentalJsExport


@OptIn(ExperimentalJsExport::class)
class LocalViewModel: ViewModel(), KoinComponent {
    private val contactRepository: IContactRepository by lazy { get() }
    val localFlow = contactRepository.getAllContact()

    fun onLocalDelete(id: Long) {
        viewModelScope.launch {
            contactRepository.deleteContact(id)
        }
    }

    @OptIn(ExperimentalJsExport::class)
    fun onLocalUpdate(contact: Contact) {
        viewModelScope.launch {
            contactRepository.updateContact(contact)
        }
    }
}