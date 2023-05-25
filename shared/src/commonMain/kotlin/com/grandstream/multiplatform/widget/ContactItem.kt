package com.grandstream.multiplatform.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grandstream.multiplatform.entity.Contact
import com.grandstream.multiplatform.util.ContactType
import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
@Composable
fun AddRemoteContactItem(onRemoteAdd: (contact: Contact) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var name by remember { mutableStateOf("") }
        var number by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        EditText(value = name, onValueChange = { name = it} )
        EditText(value = number, onValueChange = { number = it} )
        EditText(value = email, onValueChange = { email = it} )
        EditText(value = address, onValueChange = { address = it} )
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = null,
            modifier = Modifier.clickable {
                onRemoteAdd(Contact(0, name, number, email, address))
                name = ""
                number = ""
                email = ""
                address = ""
            }
        )
    }
}

@OptIn(ExperimentalJsExport::class)
@Composable
fun ContactItem(
    item: Contact,
    type: ContactType,
    onRemoteDelete: (id: Long) -> Unit = {},
    onRemoteUpdate: (contact: Contact) -> Unit = {},
    onLocalAdd: (contact: Contact) -> Unit = {},
    onLocalDelete: (id: Long) -> Unit = {},
    onLocalUpdate: (contact: Contact) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isEdit by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf(item.name) }
        var number by remember { mutableStateOf(item.number) }
        var email by remember { mutableStateOf(item.email) }
        var address by remember { mutableStateOf(item.address) }
        if (isEdit) {
            EditText(value = name, onValueChange = { name = it} )
            EditText(value = number, onValueChange = { number = it} )
            EditText(value = email, onValueChange = { email = it} )
            EditText(value = address, onValueChange = { address = it} )
        } else {
            Text(text = item.name, fontSize = 18.sp)
            Text(text = item.number, fontSize = 18.sp)
            Text(text = item.email, fontSize = 18.sp)
            Text(text = item.address, fontSize = 18.sp)
        }
        if (isEdit) {
            Row {
                Icon(
                    imageVector = Icons.Sharp.Edit,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (type == ContactType.RemoteContact) {
                            onRemoteUpdate(Contact(item.id, name, number, email, address))
                        } else {
                            onLocalUpdate(Contact(item.id, name, number, email, address))
                        }
                        isEdit = false
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Sharp.ExitToApp, contentDescription = null, modifier = Modifier.clickable {
                    isEdit = false
                    name = item.name
                    number = item.name
                    email = item.email
                    address = item.address
                })
            }
        } else {
            Row {
                Icon(imageVector = Icons.Sharp.Edit, contentDescription = null, modifier = Modifier.clickable { isEdit = true })
                if (type == ContactType.RemoteContact) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Sharp.Add, contentDescription = null, modifier = Modifier.clickable { onLocalAdd(item) })
                }
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Sharp.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (type == ContactType.RemoteContact) {
                            onRemoteDelete(item.id)
                        } else {
                            onLocalDelete(item.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun EditText(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        textStyle = TextStyle.Default.copy(fontSize = 18.sp)
    )
}