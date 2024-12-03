package net.iessochoa.davidpagan.tareasv01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.davidpagan.tareasv01.ui.theme.navigation.AppNavigationx
import net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea.TaskScreen
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.TareasV01Theme
import java.lang.reflect.Modifier


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareasV01Theme {

                AppNavigationx()

            }
        }
    }
}