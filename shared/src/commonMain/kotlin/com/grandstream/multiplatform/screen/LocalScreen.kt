package com.grandstream.multiplatform.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grandstream.multiplatform.util.ContactType
import com.grandstream.multiplatform.viewmodel.LocalViewModel
import com.grandstream.multiplatform.widget.ContactItem
import moe.tlaster.precompose.viewmodel.viewModel
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
@Composable
fun LocalScreen() {
    val localViewModel = viewModel { LocalViewModel() }
    val localList = localViewModel.localFlow.collectAsState(initial = listOf()).value
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        itemsIndexed(localList) { index, item ->
            ContactItem(
                item = item,
                type = ContactType.LocalContact,
                onLocalDelete = { localViewModel.onLocalDelete(it) },
                onLocalUpdate = { localViewModel.onLocalUpdate(it) }
            )
            Divider(thickness = 1.dp)
        }
    }
}