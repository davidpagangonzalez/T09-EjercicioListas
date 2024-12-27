package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.listatareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.AppBar
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.TareasV01Theme


@Composable
fun ListaTareasScreen(
    viewModel: ListaTareasViewModel = viewModel(),
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val uiStateLista by viewModel.listaTareasUiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                //muestra el título de la pantalla
                tituloPantallaActual = "Tareas",
                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                //navega a la pantalla de PalabraScreen para añadir una nueva palabra
                onClick = onClickNueva
            ) {
                Icon(Icons.Filled.Add, "Nueva Tarea")
            }
        }
    ) { padding ->
        Column(modifier = modifier.fillMaxSize().padding(padding)) {
            uiStateLista.listaTareas.forEachIndexed { index, tarea ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = tarea.id.toString() + " - " + tarea.prioridad + " - " + tarea.categoria + " " + tarea.tecnico     ,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            //navegamos a la pantalla de PalabraScreen para modificar la tarea
                            .clickable {
                                onItemModificarClick(index)
                            }
                    )
                }
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaPalabrasScreenPreview() {
    TareasV01Theme {
        ListaTareasScreen(
            onClickNueva = {})
    }
}
