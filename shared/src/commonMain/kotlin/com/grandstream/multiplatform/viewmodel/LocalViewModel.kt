package com.grandstream.multiplatform.viewmodel

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.util.DatabaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import kotlin.js.ExperimentalJsExport


class LocalViewModel: ViewModel() {
    val localFlow = DatabaseUtil.dbQuery.getAllContact().asFlow().mapToList(Dispatchers.Default)

    fun onLocalDelete(id: Long) {
        viewModelScope.launch {
            DatabaseUtil.dbQuery.deleteContact(id)
        }
    }

    @OptIn(ExperimentalJsExport::class)
    fun onLocalUpdate(contact: Contact) {
        viewModelScope.launch {
            DatabaseUtil.dbQuery.updateContact(contact.name, contact.number, contact.email, contact.address, contact.id)
        }
    }
}