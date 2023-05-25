import androidx.compose.runtime.Composable
import com.grandstream.multiplatform.Navigation
import com.grandstream.multiplatform.createDriver

@Composable
fun MainView() = Navigation(createDriver())