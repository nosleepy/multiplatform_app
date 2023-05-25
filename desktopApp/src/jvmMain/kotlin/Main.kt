import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.PreComposeWindow

@Composable
@Preview
fun App() {
    MainView()
}

fun main() = application {
    PreComposeWindow(title = "ContactApp", onCloseRequest = ::exitApplication) {
        Napier.base(DebugAntilog())
        MainView()
    }
}
