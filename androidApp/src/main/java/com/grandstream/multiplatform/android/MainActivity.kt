package com.grandstream.multiplatform.android

import android.os.Bundle
//import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.grandstream.multiplatform.MainView
import com.grandstream.multiplatform.Navigation
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napier.base(DebugAntilog())
        setContent {
            MainView()
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainView()
}