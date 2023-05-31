package com.grandstream.multiplatform.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.grandstream.multiplatform.di.DatabaseWrapper
import com.grandstream.multiplatform.entity.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
interface IContactRepository {
    fun getAllContact(): Flow<List<Contact>>
    fun deleteContact(id: Long)
    fun updateContact(contact: Contact)
    fun insertContact(contact: Contact)
}

@OptIn(ExperimentalJsExport::class)
class ContactRepository: IContactRepository, KoinComponent {
    private val contactQueries = DatabaseWrapper.instance.contactDatabaseQueries

    override fun getAllContact(): Flow<List<Contact>> {
        return contactQueries.getAllContact(
            mapper = {
                id, name, number, email, address -> Contact(id, name!!, number!!, email!!, address!!)
            }
        ).asFlow().mapToList(Dispatchers.Default)
    }

    override fun deleteContact(id: Long) {
        contactQueries.deleteContact(id)
    }

    override fun updateContact(contact: Contact) {
        contactQueries.updateContact(contact.name, contact.number, contact.email, contact.address, contact.id)
    }

    override fun insertContact(contact: Contact) {
        contactQueries.insertContact(contact.name, contact.number, contact.email, contact.address)
    }
}