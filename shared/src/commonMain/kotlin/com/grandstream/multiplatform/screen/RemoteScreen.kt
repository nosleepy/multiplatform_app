package com.grandstream.multiplatform.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grandstream.multiplatform.util.ContactType
import com.grandstream.multiplatform.viewmodel.RemoteViewModel
import com.grandstream.multiplatform.widget.AddRemoteContactItem
import com.grandstream.multiplatform.widget.ContactItem
import moe.tlaster.precompose.viewmodel.viewModel
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
@Composable
fun RemoteScreen() {
    val remoteViewModel = viewModel { RemoteViewModel() }
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(remoteViewModel.remoteList) { index, item ->
                ContactItem(
                    item = item,
                    type = ContactType.RemoteContact,
                    onRemoteDelete = { remoteViewModel.onRemoteDelete(it) },
                    onRemoteUpdate = { remoteViewModel.onRemoteUpdate(it) },
                    onLocalAdd = { remoteViewModel.onLocalAdd(it) }
                )
                Divider(thickness = 1.dp)
            }
        }
        AddRemoteContactItem(onRemoteAdd = { remoteViewModel.onRemoteAdd(it) })
    }
}