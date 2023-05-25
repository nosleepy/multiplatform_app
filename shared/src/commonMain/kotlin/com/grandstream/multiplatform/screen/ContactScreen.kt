package com.grandstream.multiplatform.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.grandstream.multiplatform.database.ContactDatabase
import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.util.ContactType
import com.grandstream.multiplatform.util.DatabaseUtil
import com.grandstream.multiplatform.util.HttpUtil
import com.grandstream.multiplatform.util.LogUtil
import com.grandstream.multiplatform.viewmodel.RemoteViewModel
import com.grandstream.multiplatform.widget.AddRemoteContactItem
import com.grandstream.multiplatform.widget.ContactItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModel
import kotlin.js.ExperimentalJsExport
@OptIn(ExperimentalJsExport::class)
@Composable
fun ContactScreen() {
    LogUtil.d("app init")
    val remoteViewModel = viewModel {
        RemoteViewModel()
    }
//    var remoteList by remember { mutableStateOf(listOf<Contact>()) }
    val dbQuery = DatabaseUtil.dbQuery
//    var localList by remember { mutableStateOf(listOf<Contact>()) }
//    localList = DatabaseUtil.dbQuery.getAllContact().executeAsList().map {
//        Contact(it.id, it.name!!, it.number!!, it.email!!, it.address!!)
//    }
    val localList = dbQuery.getAllContact().asFlow().mapToList(Dispatchers.Default).collectAsState(initial = listOf()).value.map {
        Contact(it.id, it.name!!, it.number!!, it.email!!, it.address!!)
    }
    val scope = rememberCoroutineScope()
    Surface(modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 20.dp, end = 20.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (remoteViewModel.remoteList.isNotEmpty()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "network contact", fontSize = 20.sp, color = Color.Red)
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(remoteViewModel.remoteList) { index, item ->
                            ContactItem(
                                item = item,
                                type = ContactType.RemoteContact,
                                onRemoteDelete = { remoteViewModel.onRemoteDelete(it) },
                                onRemoteUpdate = { remoteViewModel.onRemoteUpdate(it) },
                                onLocalAdd = {
                                    scope.launch {
                                        dbQuery.insertContact(it.name, it.number, it.email, it.address)
                                    }
                                }
                            )
                            Divider(thickness = 0.5.dp)
                        }
                    }
                    AddRemoteContactItem(onRemoteAdd = { remoteViewModel.onRemoteAdd(it) })
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = "local contact", fontSize = 20.sp, color = Color.Red)
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(localList) { index, item ->
                            ContactItem(
                                item = item,
                                type = ContactType.LocalContact,
                                onLocalDelete = { dbQuery.deleteContact(it) },
                                onLocalUpdate = { contact ->
                                    dbQuery.updateContact(contact.name, contact.number, contact.email, contact.address, contact.id)
                                }
                            )
                            Divider(thickness = 0.5.dp)
                        }
                    }
                }
            } else {
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        scope.launch {
                            remoteViewModel.remoteList = HttpUtil.getContactList()
                        }
                    }
                ) {
                    Text(text = "Load")
                }
                Row {
                    Text(text = remoteViewModel.name)
                    Button(onClick = { remoteViewModel.name = "world" }) {
                        Text(text = "click")
                    }
                }
            }
        }
    }
}