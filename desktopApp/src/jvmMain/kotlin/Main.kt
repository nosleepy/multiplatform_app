import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.application
import com.grandstream.multiplatform.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.PreComposeWindow

@Composable
@Preview
fun App() {
    MainView()
}

fun main() = application {
    initKoin()
    Napier.base(DebugAntilog())
    PreComposeWindow(title = "ContactApp", onCloseRequest = ::exitApplication) {
        MainView()
    }
}
