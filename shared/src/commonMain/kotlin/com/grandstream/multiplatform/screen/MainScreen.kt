package com.grandstream.multiplatform.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MainScreen(navigator: Navigator) {
    Column(modifier = Modifier.fillMaxSize()) {
        var pagerState by remember { mutableStateOf(0) }
        TitleBar(title = "contact list", onBack = { navigator.goBack() })
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp).background(Color.Red))
        TopTitle()
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp))
        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            when (pagerState) {
                0 -> RemoteScreen()
                1 -> LocalScreen()
            }
        }
        BottomNavigation(current = pagerState, currentChanged = { pagerState = it })
    }
}

@Composable
fun TopTitle() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "name", fontSize = 18.sp)
        Text(text = "number", fontSize = 18.sp)
        Text(text = "email", fontSize = 18.sp)
        Text(text = "address", fontSize = 18.sp)
        Text(text = "operate", fontSize = 18.sp)
    }
}

@Composable
fun BottomNavigation(
    current: Int,
    currentChanged: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        BottomNavigationItem(
            modifier = Modifier.weight(1f).clickable { currentChanged(0) },
            imageVector = Icons.Sharp.Add,
            label = "RemoteContact",
            tint = if (current == 0) Color.Red else Color.Gray
        )
        BottomNavigationItem(
            modifier = Modifier.weight(1f).clickable { currentChanged(1) },
            imageVector = Icons.Sharp.Close,
            label = "LocalContact",
            tint = if (current == 1) Color.Red else Color.Gray,
        )
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier,
    imageVector: ImageVector,
    label: String,
    tint: Color,
) {
    Column(
        modifier = modifier.padding(0.dp, 4.dp, 0.dp, 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = tint
        )
        Text(
            text = label,
            fontSize = 16.sp,
            color = tint
        )
    }
}

@Composable
fun TitleBar(
    title: String,
    onBack: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                modifier = Modifier.size(20.dp).clickable { onBack() },
                contentDescription = "Back",
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}